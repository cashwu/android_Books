package com.cashwu.books.data.remote

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

/**
 * Default implementation of WebSocketListener to handle WebSocket events.
 * This class provides default behavior for WebSocket events and can be extended
 * to customize the behavior.
 */
class DefaultWebSocketListener : WebSocketListener() {
    
    companion object {
        private const val TAG = "DefaultWebSocketListener"
    }
    
    private val webSocketManager = WebSocketManager.getInstance()
    
    /**
     * Called when a WebSocket connection is established.
     */
    override fun onOpen(webSocket: WebSocket, response: Response) {
        Log.d(TAG, "WebSocket connection established")
        webSocketManager.setConnected(true)
    }
    
    /**
     * Called when a text message is received.
     */
    override fun onMessage(webSocket: WebSocket, text: String) {
        Log.d(TAG, "Received message: $text")
        // Handle the received message here
        // For example, parse JSON, update UI, etc.
    }
    
    /**
     * Called when a binary message is received.
     */
    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.d(TAG, "Received binary message: ${bytes.hex()}")
        // Handle the received binary message here
    }
    
    /**
     * Called when the WebSocket connection is closing.
     */
    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.d(TAG, "WebSocket closing: $code - $reason")
        webSocket.close(code, reason)
    }
    
    /**
     * Called when the WebSocket connection is closed.
     */
    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Log.d(TAG, "WebSocket closed: $code - $reason")
        webSocketManager.setConnected(false)
    }
    
    /**
     * Called when a WebSocket error occurs.
     */
    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.e(TAG, "WebSocket error: ${t.message}", t)
        webSocketManager.setConnected(false)
        
        // Attempt to reconnect after a delay
        // This could be improved with exponential backoff
        Thread {
            try {
                Thread.sleep(5000) // Wait 5 seconds before reconnecting
                webSocketManager.connect(this)
            } catch (e: InterruptedException) {
                Log.e(TAG, "Reconnection interrupted", e)
            }
        }.start()
    }
}