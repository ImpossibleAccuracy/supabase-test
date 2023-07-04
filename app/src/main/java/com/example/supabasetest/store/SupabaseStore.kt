package com.example.supabasetest.store

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.serializer.JacksonSerializer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseStore {
    @Provides
    @Singleton
    fun provideSupabase(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://eojrsogefmmctzglurjv.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVvanJzb2dlZm1tY3R6Z2x1cmp2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODgzNzEzMzksImV4cCI6MjAwMzk0NzMzOX0.zsV8O4qqcrERGrnwHQ69npNG1t3EWMdtdKumsqPRyHo"
        ) {
            jacksonObjectMapper().apply {
                disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

                defaultSerializer = JacksonSerializer(this)
            }

            install(GoTrue) {
                alwaysAutoRefresh = false // default: true
                autoLoadFromStorage = false // default: true
            }

            install(Postgrest)
            install(Realtime)
        }
    }
}