package com.example.knowit.util

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.coroutineScope

class ConvertToBitmap{
     companion object {
          suspend fun getBitmap(string: String?, context: Context) : String? {
              var imageName :String?
              val result : Drawable
              return if(string.isNullOrEmpty()){
                  null
              } else {
                  coroutineScope {
                      val loading = ImageLoader(context)
                      val request = ImageRequest.Builder(context).data(string).build()
                      try {
                          result = (loading.execute(request) as SuccessResult).drawable
                          imageName = ImageStorageManager.saveToInternalStorage(
                              context,
                              (result as BitmapDrawable).bitmap,
                              result.bitmap.toString()
                          )
                      }catch (e:Exception){
                          imageName = null
                      }
                  }
                  imageName
              }
          }
     }
}