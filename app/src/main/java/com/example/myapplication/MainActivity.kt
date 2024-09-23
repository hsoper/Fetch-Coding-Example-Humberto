@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.models.Item
import com.example.myapplication.models.Items
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.models.WebData

class MainActivity : ComponentActivity() {
    private val webhelper : WebData by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold( modifier = Modifier.fillMaxSize(), topBar = { TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Box(modifier =  Modifier.fillMaxSize() , contentAlignment = Alignment.Center  ) {
                            Text( text = "Humberto's Fetch Rewards Coding Exercise", textAlign = TextAlign.Center)
                        }
                    })}
                )
                { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        if(webhelper.helper == ""){
                            item {
                                Box(modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp), contentAlignment = Alignment.Center) {
                                    Text("You either have no internet or the website is down; please reload.", textAlign = TextAlign.Center, fontSize = 20.sp)
                                }
                            }
                        }
                        else {
                            val items = Items(webhelper.getJson())
                            val itemList: List<Item> = items.getSortedList()
                            items(itemList.size) { index ->
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    SizedTextBox(85,50,"List Id : " + itemList[index].listId.toString())
                                    SizedTextBox(70,50,"Id : " + itemList[index].id.toString())
                                    SizedTextBox(150,50,"Name : " + itemList[index].name)
                                }
                            }
                        }

                    }
                }
            }
        }
    }
    @Composable
    fun SizedTextBox(width : Int, height : Int, text : String ){
        Box(modifier = Modifier.size(width.dp,height.dp), contentAlignment = Alignment.CenterStart) {
            Text(text, fontSize = 18.sp)
        }
    }
}

