package com.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Composable
fun DiaryEntry(entryModel: EntryModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )

    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(color = EntryItem)
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
                text = "Current Tempo",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(currentTempoLabel) {
                    top.linkTo(title.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 24.dp)
                })

            Text(
                text = entryModel.initTempo,
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(currentTempo) {
                    top.linkTo(currentTempoLabel.bottom)
                    start.linkTo(currentTempoLabel.start, )
                    end.linkTo(currentTempoLabel.end, )
                })

            Text(
                text = "Target Tempo",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(targetTempoLabel) {
                    top.linkTo(title.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                })

            Text(
                text = entryModel.targetTempo,
                fontSize = 16.sp,
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
    DiaryEntry(EntryModel(1, "Guitar Solo", "80", "100"))
}