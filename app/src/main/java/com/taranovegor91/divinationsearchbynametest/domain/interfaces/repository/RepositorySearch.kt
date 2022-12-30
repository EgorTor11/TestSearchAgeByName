package com.taranovegor91.divinationsearchbynametest.domain.interfaces.repository

interface RepositorySearch {
  suspend   fun getDivination(name: String):String
}