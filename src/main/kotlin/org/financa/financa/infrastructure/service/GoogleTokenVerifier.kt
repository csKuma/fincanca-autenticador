package org.financa.financa.infrastructure.service


import org.springframework.stereotype.Component
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import jakarta.annotation.PostConstruct
import java.io.FileInputStream

@Component
class GoogleTokenVerifier {


    val path = System.getenv("CONFIG_FIREBASE")

    @PostConstruct
    fun init() {
        println("🔥 Caminho do Firebase: ${path}")
        if (FirebaseApp.getApps().isEmpty()) {
            val serviceAccount = FileInputStream(path) // ajuste o caminho se necessário

            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()

            FirebaseApp.initializeApp(options)
        }
    }

    fun verify(idToken: String): FirebaseToken? {
        return try {
            val decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken)
            println("✅ Token válido! UID: ${decodedToken.uid}")
            decodedToken
        } catch (e: Exception) {
            println("❌ Erro na verificação do token: ${e.message}")
            null
        }
    }
}