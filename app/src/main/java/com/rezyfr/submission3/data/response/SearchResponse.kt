package com.rezyfr.submission3.data.response

import com.rezyfr.submission3.data.model.UserModel

data class SearchResponse (
    val incomplete_results: Boolean,
    val items: List<UserModel>,
    val total_count: Int
)