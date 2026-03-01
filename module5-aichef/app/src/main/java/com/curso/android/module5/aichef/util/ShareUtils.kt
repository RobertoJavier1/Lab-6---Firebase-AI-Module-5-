package com.curso.android.module5.aichef.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.content.FileProvider
import java.io.File

//utilidad: para compartir una imagen via el share sheet de Android.
//flujo: Bitmap → archivo temporal en cache → URI segura (FileProvider) → Intent.ACTION_SEND
 //el archivo temporal se elimina en la próxima llamada para no acumular basura en cache
object ShareUtils {

    private const val SHARED_IMAGES_DIR = "shared_images"
    private const val SHARE_FILE_NAME = "recipe_share.jpg"

    //guarda el bitmap como JPEG temporal y lanza el share sheet
    fun shareRecipeImage(context: Context, bitmap: Bitmap, recipeTitle: String) {
        val uri = saveBitmapToCache(context, bitmap) ?: return

        //Intent.ACTION_SEND: lanza el selector de apps para compartir
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/jpeg"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, recipeTitle)
            putExtra(Intent.EXTRA_TEXT, "Mira esta receta: $recipeTitle")
            //otorga permiso de lectura temporal a la app destino
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(Intent.createChooser(intent, "Compartir receta"))
    }


    //escribe el bitmap en cache/shared_images/ y devuelve su URI via FileProvider
    //elimina cualquier archivo previo para no acumular archivos temporales
    private fun saveBitmapToCache(context: Context, bitmap: Bitmap): android.net.Uri? {
        return try {
            val sharedDir = File(context.cacheDir, SHARED_IMAGES_DIR).apply { mkdirs() }

            //limpiar archivo anterior
            val file = File(sharedDir, SHARE_FILE_NAME)
            if (file.exists()) file.delete()

            //comprimir y guardar como JPEG
            file.outputStream().use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            }

            //fileProvider convierte la ruta real en una URI segura que otras apps pueden leer
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
        } catch (e: Exception) {
            null
        }
    }
}