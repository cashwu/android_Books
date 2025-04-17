package com.cashwu.books

import android.app.Application
import android.util.Log
import com.cashwu.books.data.remote.DefaultWebSocketListener
import com.cashwu.books.data.remote.WebSocketManager

/**
 * Custom Application class for the Books app.
 * This class is responsible for initializing app-wide components like the WebSocket connection.
 */
class BooksApplication : Application() {
    
    companion object {
        private const val TAG = "BooksApplication"
    }
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize WebSocket connection
        initWebSocket()
        
        Log.d(TAG, "BooksApplication initialized")
    }
    
    /**
     * Initializes the WebSocket connection.
     */
    private fun initWebSocket() {
        try {
            val webSocketManager = WebSocketManager.getInstance()
            val webSocketListener = DefaultWebSocketListener()
            
            // Connect to the WebSocket server
            webSocketManager.connect(webSocketListener)
            
            Log.d(TAG, "WebSocket initialization started")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize WebSocket", e)
        }
    }
    
    override fun onTerminate() {
        // Disconnect WebSocket when the application is terminated
        WebSocketManager.getInstance().disconnect()
        Log.d(TAG, "WebSocket disconnected on application terminate")
        
        super.onTerminate()
    }
}