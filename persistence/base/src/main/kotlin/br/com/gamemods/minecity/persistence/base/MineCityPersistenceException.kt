package br.com.gamemods.minecity.persistence.base

import java.io.IOException

public class MineCityPersistenceException(message: String? = null, cause: Throwable? = null): IOException(message, cause)
