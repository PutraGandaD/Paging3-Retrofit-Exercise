package com.putragandad.pagingretrofit.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.putragandad.pagingretrofit.domain.model.Repo
import com.putragandad.pagingretrofit.presentation.theme.JetpackComposePagingRetrofitTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val vm = koinViewModel<MainViewModel>()
            val state by vm.state.collectAsStateWithLifecycle()
            val lazyItems = vm.repos.collectAsLazyPagingItems()
            MyApp(
                uiState = state,
                onSearchQueryChange = { query ->
                    vm.changeSearchQuery(query)
                },
                items = lazyItems
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }
}

@Composable
fun MyApp(
    uiState: MainUiState,
    onSearchQueryChange : (String) -> Unit,
    items: LazyPagingItems<Repo>
) {
    JetpackComposePagingRetrofitTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { contentPadding ->
            Surface(
                modifier = Modifier
                    .padding(contentPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = onSearchQueryChange,
                        label = { Text("Search Github Repo...") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    LazyColumn {
                        items(items.itemCount) { index ->
                            val repo = items[index]
                            if (repo != null) Text(repo.username)
                        }

                        items.apply {
                            when {
                                loadState.refresh is LoadState.Loading -> {
                                    if(!uiState.searchQuery.isNullOrEmpty()) {
                                        item {
                                            Box(
                                                modifier = Modifier
                                                    .fillParentMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator()
                                            }
                                        }
                                    }
                                }
                                loadState.append is LoadState.Loading -> {
                                    item {
                                        Box(modifier = Modifier.fillMaxWidth()) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.align(Alignment.Center)
                                            )
                                        }
                                    }
                                }
                                loadState.refresh is LoadState.Error -> {
                                    val e = items.loadState.refresh as LoadState.Error
                                    item {
                                        Text(
                                            text = "Error: ${e.error.localizedMessage}",
                                            modifier = Modifier.padding(16.dp),
                                            color = Color.Red
                                        )
                                    }
                                }
                                loadState.append is LoadState.Error -> {
                                    val e = items.loadState.append as LoadState.Error
                                    item {
                                        Text(
                                            text = "Error while loading more: ${e.error.localizedMessage}",
                                            modifier = Modifier.padding(16.dp),
                                            color = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun MyAppPreview() {
//    JetpackComposePagingRetrofitTheme {
//        // fake UI state
//        val fakeUiState = MainUiState(searchQuery = "compose")
//
//        // fake PagingData
//        val fakeItems = remember {
//            PagingData.from(
//                listOf(
//                    Repo(username = "compose-samples"),
//                    Repo(username = "compose-samples2"),
//                    Repo(username = "compose-samples3"),
//                )
//            )
//        }
//
//        // convert PagingData -> LazyPagingItems
//        val lazyItems = fakeItems.()
//
//        MyApp(
//            uiState = fakeUiState,
//            onSearchQueryChange = {},
//            items = lazyItems
//        )
//    }
//}
