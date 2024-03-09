package com.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.feature.home.domain.model.EntryModel
import ui.theme.EntryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryEntry(
    entryModel: EntryModel,
    onEntryClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .background(color = EntryItem),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = EntryItem
        ),
        onClick = { onEntryClick(entryModel.id.toString()) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            val (title, currentTempoLabel, currentTempo, targetTempoLabel, targetTempo) = createRefs()
            Text(
                text = entryModel.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            Text(
                text = "Init bpm",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(currentTempoLabel) {
                    top.linkTo(title.bottom, margin = 24.dp)
                    start.linkTo(parent.start, margin = 4.dp)
                })

            Text(
                text = entryModel.initTempo,
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(currentTempo) {
                    top.linkTo(currentTempoLabel.bottom)
                    start.linkTo(currentTempoLabel.start, )
                    end.linkTo(currentTempoLabel.end, )
                })

            Text(
                text = "Target bpm",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(targetTempoLabel) {
                    top.linkTo(title.bottom, margin = 24.dp)
                    end.linkTo(parent.end, margin = 4.dp)
                })

            Text(
                text = entryModel.targetTempo,
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(targetTempo) {
                    top.linkTo(targetTempoLabel.bottom)
                    start.linkTo(targetTempoLabel.start, )
                    end.linkTo(targetTempoLabel.end, )
                })
        }
    }

}

@Preview
@Composable
fun DiaryEntryPreview() {
    DiaryEntry(EntryModel(1, "Guitar Solo", "80", "100"), {})
}