package com.example.knowit.util

import com.example.knowit.data.remote.model.Article


sealed class Resource {
    class Success(data: List<Article>?) : Resource()
    class Error(message: String) : Resource()
}