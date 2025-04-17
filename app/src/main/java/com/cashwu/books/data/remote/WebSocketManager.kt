package com.cashwu.books.data.remote

import android.util.Log
import okhttp3.*
import java.util.concurrent.TimeUnit

/**
 * Manager class for handling WebSocket connections to the server.
 * This class is responsible for establishing, maintaining, and closing WebSocket connections.
 */
class WebSocketManager private constructor() {

    companion object {
        private const val TAG = "WebSocketManager"
        
        // Server URLs as constant strings
        private const val WS_URL = "ws://example.com/ws"
        private const val SERVER_URL = "https://example.com/api"
        
        // Singleton instance
        @Volatile
        private var instance: WebSocketManager? = null
        
        fun getInstance(): WebSocketManager {
            return instance ?: synchronized(this) {
                instance ?: WebSocketManager().also { instance = it }
            }
        }
    }
    
    private var webSocket: WebSocket? = null
    private val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS) // No timeout for WebSocket connections
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()
    
    private var isConnected = false
    
    /**
     * Connects to the WebSocket server.
     * @param listener The listener to handle WebSocket events.
     */
    fun connect(listener: WebSocketListener) {
        if (isConnected) {
            Log.d(TAG, "WebSocket is already connected")
            return
        }
        
        val request = Request.Builder()
            .url(WS_URL)
            .build()
        
        webSocket = client.newWebSocket(request, listener)
        Log.d(TAG, "Connecting to WebSocket server: $WS_URL")
    }
    
    /**
     * Disconnects from the WebSocket server.
     */
    fun disconnect() {
        webSocket?.close(1000, "Normal closure")
        webSocket = null
        isConnected = false
        Log.d(TAG, "Disconnected from WebSocket server")
    }
    
    /**
     * Sends a message through the WebSocket connection.
     * @param message The message to send.
     * @return true if the message was sent successfully, false otherwise.
     */
    fun sendMessage(message: String): Boolean {
        if (webSocket == null || !isConnected) {
            Log.e(TAG, "Cannot send message: WebSocket is not connected")
            return false
        }
        
        val success = webSocket?.send(message) ?: false
        if (success) {
            Log.d(TAG, "Message sent: $message")
        } else {
            Log.e(TAG, "Failed to send message: $message")
        }
        
        return success
    }
    
    /**
     * Updates the connection status.
     * @param connected The new connection status.
     */
    fun setConnected(connected: Boolean) {
        isConnected = connected
    }
    
    /**
     * Checks if the WebSocket is currently connected.
     * @return true if connected, false otherwise.
     */
    fun isConnected(): Boolean {
        return isConnected
    }
    
    /**
     * Gets the server URL.
     * @return The server URL.
     */
    fun getServerUrl(): String {
        return SERVER_URL
    }
}