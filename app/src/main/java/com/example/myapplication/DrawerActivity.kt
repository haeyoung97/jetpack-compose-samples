package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.launch

class DrawerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                DrawerView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerView() {
    val navController = rememberNavController()
    Surface {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        NavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerMenu(onDestinationClicked = { route ->
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
        ) {
            NavigationGraph(
                navController = navController,
                openDrawer = { openDrawer() }
            )
        }
    }
}

sealed class DrawerMenuItem(val title: String, val route: String) {
    object Home : DrawerMenuItem("Home", "HOME")
    object Customer : DrawerMenuItem("Customer", "CUSTOMER")
    object Help : DrawerMenuItem("Help", "HELP")
}

@Composable
private fun DrawerMenu(onDestinationClicked: (route: String) -> Unit) {
    val screens = listOf(
        DrawerMenuItem.Home,
        DrawerMenuItem.Customer,
        DrawerMenuItem.Help,
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        screens.forEach { screen ->
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = screen.title,
                color = Pink40,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(30.dp, 0.dp)
                    .clickable {
                        onDestinationClicked(screen.route)
                    }
            )
        }
    }
}

@Composable
private fun Home(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "HOME",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = PurpleGrey80),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home!", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
private fun Customer(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "CUSTOMER",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = PurpleGrey80),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Customer!", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
private fun Help(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "HELP",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = PurpleGrey80),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Help!", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
private fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    SmallTopAppBar(
        title = { Text(text = title, color = Purple40) },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                Icon(imageVector = buttonIcon, contentDescription = "")
            }
        },
        modifier = Modifier.background(color = Pink40)
    )
}

@Composable
private fun NavigationGraph(
    navController: NavHostController,
    openDrawer: () -> Unit
) {
    NavHost(navController = navController, startDestination = DrawerMenuItem.Home.route) {
        composable(DrawerMenuItem.Home.route) {
            Home(openDrawer = { openDrawer() })
        }
        composable(DrawerMenuItem.Customer.route) {
            Customer(openDrawer = { openDrawer() })
        }
        composable(DrawerMenuItem.Help.route) {
            Help(openDrawer = { openDrawer() })
        }
    }
}