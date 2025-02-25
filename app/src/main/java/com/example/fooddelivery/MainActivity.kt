package com.example.fooddelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.fooddelivery.data.OrderState
import com.example.fooddelivery.screen.ProductDetailsScreen

import com.example.fooddelivery.ui.theme.AppTheme

private const val PRODUCT_PRICE_PER_UNIT = 5.25
private const val PRODUCT_CURRENCY = "$"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                var amount by remember { mutableIntStateOf(5) }
                val totalPrice by remember { derivedStateOf { amount * PRODUCT_PRICE_PER_UNIT } }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductDetailsScreen(
                        modifier = Modifier.padding(innerPadding),
                        orderState = OrderState(
                            amount = amount,
                            totalPrice = "$PRODUCT_CURRENCY${totalPrice}"
                        ),
                        onAddItemClicked = { amount = amount.inc() },
                        onRemoveItemClicked = { if (amount > 0) amount = amount.dec() })

                }
            }
        }
    }
}

