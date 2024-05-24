package com.narrowstudio.sisenor.word.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.narrowstudio.sisenor.database.WordDatabase
import com.narrowstudio.sisenor.word.domain.Word
import com.narrowstudio.sisenor.word.domain.WordDataSource
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SQLDelightWordDataSource(
    db: WordDatabase
): WordDataSource {

    private val queries = db.wordListQueries
    override suspend fun getWords(): Flow<List<Word>> {
        return queries
            .getWords()
            .asFlow()
            .mapToList(currentCoroutineContext())
            .map { wordEntities ->
                wordEntities.map {wordEntity ->
                    wordEntity.toWord()
                }
            }
    }

    override suspend fun getRangeWords(startIndex: Long, endIndex: Long): Flow<List<Word>> {
        return queries
            .getWordsAtRange(startIndex, endIndex)
            .asFlow()
            .mapToList(currentCoroutineContext())
            .map { wordEntities ->
                wordEntities.map {wordEntity ->
                    wordEntity.toWord()
                }
            }
    }


    override suspend fun getSimilarWords(): Flow<List<Word>> {
        return queries
            .getSimilarWords()
            .asFlow()
            .mapToList(currentCoroutineContext())
            .map { wordEntities ->
                wordEntities.map {wordEntity ->
                    wordEntity.toWord()
                }
            }
    }

    override suspend fun insertWord(jsonString: String) {
        val wordList = parseJson(jsonString)
        for (word in wordList) {
            val similar: Long = if (word.isSimilar) 1 else 0
            val learned: Long = if (word.isLearned == true) 1 else 0
            queries.updateWord(word.id, word.spanishWord, word.englishWord, similar, learned, word.audioBytes.toString())
        }
    }


}