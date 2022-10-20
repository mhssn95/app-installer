package io.mhssn.app

import android.app.Application
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageInstaller
import android.content.res.AssetFileDescriptor
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val app: Application) : AndroidViewModel(app) {

  fun install(fd: AssetFileDescriptor) {
    viewModelScope.launch(Dispatchers.IO) {
      val length = fd.length
      val stream = fd.createInputStream()
      val params = PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL)
      val sessionId = app.packageManager.packageInstaller.createSession(params)
      val session = app.packageManager.packageInstaller.openSession(sessionId)

      session.openWrite("app", 0, length).use { sessionStream ->
        stream.copyTo(sessionStream)
        session.fsync(sessionStream)
      }

      val intent = Intent(getApplication(), PackageInstallerReceiver::class.java)
      val pi = PendingIntent.getBroadcast(
        getApplication(),
        1234,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
      )

      session.commit(pi.intentSender)
      session.close()
    }
  }

}