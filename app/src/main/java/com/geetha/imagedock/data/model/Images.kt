package com.geetha.imagedock.data.model

import com.google.gson.annotations.SerializedName

data class Images (

  @SerializedName("total"     ) var total     : Int?            = null,
  @SerializedName("totalHits" ) var totalHits : Int?            = null,
  @SerializedName("hits"      ) var hits      : List<Hits> = arrayListOf()

)