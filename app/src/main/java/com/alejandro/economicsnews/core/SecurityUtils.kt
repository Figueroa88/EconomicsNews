package com.alejandro.economicsnews.core

import android.os.Build
import java.security.SecureRandom
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


object SecurityUtils
{
    private const val KEY_AES = "3@bi0ewaswiwu!9+3ohUpru3ubl5ro6_"
    private const val SALT_AES = "tr9tiprEco5ro1OgA-aZUp6e8rapaSih"

    private lateinit var mSecretKey: SecretKey

    init
    {
        val secretKeyFactory: SecretKeyFactory
        val keySpec: KeySpec

        try
        {
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
            keySpec = PBEKeySpec(KEY_AES.toCharArray(), SALT_AES.toByteArray(), 65536, 256)
            mSecretKey = secretKeyFactory.generateSecret(keySpec)
        }
        catch (error: Exception)
        {
            error.printStackTrace()
        }
    }

    fun getAES(data: String): String
    {
        try
        {
            val ivParameterSpec = generateIv()
            val secretKey = SecretKeySpec(mSecretKey.encoded, "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                Base64.getEncoder()
                    .encodeToString(cipher.doFinal(data.toByteArray(charset("UTF-8"))))
            }
            else
            {
                android.util.Base64.encodeToString(
                    cipher.doFinal(data.toByteArray(charset("UTF-8"))),
                    android.util.Base64.DEFAULT
                )
            }
        }
        catch (e: java.lang.Exception)
        {
            e.printStackTrace()
        }

        return ""
    }

    fun getAESDecrypt(data: String): String
    {
        try
        {
            val ivParameterSpec = generateIv()
            val secretKey = SecretKeySpec(mSecretKey.encoded, "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String(cipher.doFinal(Base64.getDecoder().decode(data)))
            }
            else
            {
                String(
                    cipher.doFinal(
                        android.util.Base64.decode(
                            data,
                            android.util.Base64.DEFAULT
                        )
                    )
                )
            }
        }
        catch (e: java.lang.Exception)
        {
            e.printStackTrace()
        }

        return ""
    }

    private fun generateIv(): IvParameterSpec
    {
        val iv = ByteArray(16)

        //SecureRandom().nextBytes(iv)

        return IvParameterSpec(iv)
    }
}