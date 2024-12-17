package org.example.bookstore.ui.component

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import bookstore.composeapp.generated.resources.Res
import bookstore.composeapp.generated.resources.add_icon
import bookstore.composeapp.generated.resources.book_icon
import bookstore.composeapp.generated.resources.supplier_icon
import org.example.bookstore.model.Entity
import org.example.bookstore.screen.home.HomeViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BookstoreNavigationRail(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val selectedEntity = viewModel.selectedEntity.collectAsState()
    NavigationRail(
        modifier = modifier,
    ) {
        FloatingActionButton(
            onClick = { viewModel.onAddNewEntity() },
        ) {
            Icon(
                painter = painterResource(Res.drawable.add_icon),
                contentDescription = null
            )
        }
        Entity.entries.forEach { entity ->
            NavigationRailItem(
                selected = selectedEntity.value.name == entity.name,
                onClick = {
                    viewModel.onEntitySelected(entity = entity)
                },
                icon = {
                    Icon(
                        painter = painterResource(entity.icon),
                        contentDescription = null,
                    )
                },
                label = { Text(text = stringResource(entity.title)) }
            )
        }
    }
}

