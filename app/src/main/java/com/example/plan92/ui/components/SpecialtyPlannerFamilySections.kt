package com.example.plan92.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plan92.ui.theme.plan92Palette

@Composable
fun ProjectPlannerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Project Name", "Owner")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Objective", modifier = Modifier.weight(1f)) {
                SpecialtyField("What is the goal?", "project_objective", minLines = 4)
            }
            SpecialtyCard(title = "Resources", modifier = Modifier.weight(1f)) {
                repeat(4) { index ->
                    SpecialtyField("Resource ${index + 1}", "project_resource_$index")
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Start / Due / Completed", modifier = Modifier.weight(1f)) {
                SpecialtyField("Start Date", "project_start")
                SpecialtyField("Due Date", "project_due")
                SpecialtyField("Completed", "project_completed")
            }
            SpecialtyCard(title = "Progress", modifier = Modifier.weight(1f)) {
                PriorityProgressRow("Low", "project_progress_low")
                PriorityProgressRow("Medium", "project_progress_medium")
                PriorityProgressRow("High", "project_progress_high")
            }
        }
        SpecialtyCard(title = "Brainstorm") {
            SpecialtyField("Ideas, blockers, next actions", "project_brainstorm", minLines = 7)
        }
    }
}

@Composable
fun ProjectProgressBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Week", "Lead")
        repeat(3) { index ->
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.plan92Palette.fieldSurface,
                border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Project ${index + 1}",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.plan92Palette.primaryAccent,
                            fontWeight = FontWeight.Bold,
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(999.dp))
                                .background(MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.14f))
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                        ) {
                            Text("Priority", color = MaterialTheme.plan92Palette.secondaryAccent, style = MaterialTheme.typography.labelMedium)
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        SpecialtyField("Priority", "project_progress_${index}_priority", modifier = Modifier.weight(1f))
                        SpecialtyField("Status", "project_progress_${index}_status", modifier = Modifier.weight(1f))
                    }
                    SpecialtyField("Timeline / milestone", "project_progress_${index}_timeline")
                    SpecialtyField("Notes", "project_progress_${index}_notes", minLines = 3)
                }
            }
        }
    }
}

@Composable
fun VacationBudgetBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val rows = listOf("Flights", "Hotel", "Food", "Transport", "Activities", "Shopping")
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Destination", "Travel Dates")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SummaryBox("Budget", "vacation_budget_total", Modifier.weight(1f))
            SummaryBox("Saved", "vacation_budget_saved", Modifier.weight(1f))
            SummaryBox("Balance", "vacation_budget_balance", Modifier.weight(1f))
        }
        SpecialtyTable(
            header = listOf("Category", "Planned", "Actual"),
            rows = rows,
            keyPrefix = "vacation_budget",
            firstColumnWidth = 112.dp,
            cellWidth = 96.dp,
        )
        SpecialtyCard(title = "Notes") {
            SpecialtyField("Trip notes and spending reminders", "vacation_budget_notes", minLines = 4)
        }
    }
}

@Composable
fun BillPaymentBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val bills = listOf("Rent", "Electric", "Water", "Internet", "Phone", "Insurance")
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Month", "Budget Goal")
        SpecialtyTable(
            header = listOf("Bill", "Due", "Paid", "Amt Due", "Amt Paid", "Balance", "Notes"),
            rows = bills,
            keyPrefix = "bill_tracker",
            firstColumnWidth = 96.dp,
            cellWidth = 96.dp,
        )
    }
}

@Composable
fun TravelPackingBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Destination", "Travel Date")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyChecklistCard("Documents", 5, "travel_docs", Modifier.weight(1f))
            SpecialtyChecklistCard("Clothes", 5, "travel_clothes", Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyChecklistCard("Toiletries", 5, "travel_toiletries", Modifier.weight(1f))
            SpecialtyChecklistCard("Electronics", 5, "travel_electronics", Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyChecklistCard("Medical", 4, "travel_medical", Modifier.weight(1f))
            SpecialtyChecklistCard("Financial", 4, "travel_financial", Modifier.weight(1f))
        }
        SpecialtyCard(title = "Travel Notes") {
            SpecialtyField("Reservations, reminders, or itinerary notes", "travel_notes", minLines = 4)
        }
    }
}

@Composable
fun FamilyOrganizerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Week Of", "Family Focus")
        SpecialtyDayScroller(days, "family_day")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Meals", modifier = Modifier.weight(1f)) {
                repeat(4) { index -> SpecialtyField("Meal ${index + 1}", "family_meal_$index") }
            }
            SpecialtyCard(title = "Bills / Reminders", modifier = Modifier.weight(1f)) {
                repeat(4) { index -> SpecialtyField("Reminder ${index + 1}", "family_reminder_$index") }
            }
        }
    }
}

@Composable
fun MomPlannerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Date", "Family Goal")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyChecklistCard("Today's Tasks", 6, "mom_tasks", Modifier.weight(1.2f))
            SpecialtyCard(title = "Water / Mood", modifier = Modifier.weight(0.8f)) {
                ToggleDotsRow("Water", 8, "mom_water")
                ToggleDotsRow("Mood", 5, "mom_mood")
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Meals", modifier = Modifier.weight(1f)) {
                SpecialtyField("Breakfast", "mom_breakfast")
                SpecialtyField("Lunch", "mom_lunch")
                SpecialtyField("Dinner", "mom_dinner")
            }
            SpecialtyCard(title = "Exercise / Savings", modifier = Modifier.weight(1f)) {
                SpecialtyField("Exercise", "mom_exercise")
                SpecialtyField("Savings Goal", "mom_savings")
                SpecialtyField("Money Note", "mom_money")
            }
        }
        SpecialtyCard(title = "Habits and Notes") {
            SpecialtyField("Family habits or reminders", "mom_habits", minLines = 4)
        }
    }
}

@Composable
fun MomChoresBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Week Of", "Household Focus")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            days.forEach { day ->
                SpecialtyCard(title = day, modifier = Modifier.width(170.dp)) {
                    SpecialtyField("Must do", "mom_chores_${day}_must", minLines = 3)
                    SpecialtyField("Extra time", "mom_chores_${day}_extra", minLines = 3)
                }
            }
        }
    }
}

@Composable
fun ClassScheduleBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val periods = listOf("8 AM", "10 AM", "12 PM", "2 PM", "4 PM")
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Semester", "Main Focus")
        SpecialtyMatrix("Time", days, periods, "class_schedule", 72.dp, 112.dp)
    }
}

@Composable
fun StudyPlannerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Week", "Subject Focus")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Assignments", modifier = Modifier.weight(1f)) {
                repeat(5) { index -> SpecialtyField("Assignment ${index + 1}", "study_assignment_$index") }
            }
            SpecialtyCard(title = "Exams / Due Dates", modifier = Modifier.weight(1f)) {
                repeat(4) { index -> SpecialtyField("Due ${index + 1}", "study_due_$index") }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Focus Sessions", modifier = Modifier.weight(1f)) {
                SpecialtyField("Session plan", "study_sessions", minLines = 4)
            }
            SpecialtyCard(title = "Notes", modifier = Modifier.weight(1f)) {
                SpecialtyField("What needs review?", "study_notes", minLines = 4)
            }
        }
    }
}

@Composable
fun TeacherPlannerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Week", "Class")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            days.forEach { day ->
                SpecialtyCard(title = day, modifier = Modifier.width(180.dp)) {
                    SpecialtyField("Lesson Plan", "teacher_${day}_lesson", minLines = 3)
                    SpecialtyField("Materials", "teacher_${day}_materials", minLines = 2)
                    SpecialtyField("Notes", "teacher_${day}_notes", minLines = 2)
                }
            }
        }
        SpecialtyCard(title = "Weekly Prep") {
            SpecialtyField("Announcements, grading, and prep notes", "teacher_weekly_prep", minLines = 4)
        }
    }
}

@Composable
fun MedicalNotesBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Patient / Subject", "Date")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Symptoms", modifier = Modifier.weight(1f)) {
                SpecialtyField("Observed symptoms", "medical_symptoms", minLines = 4)
            }
            SpecialtyCard(title = "Medications", modifier = Modifier.weight(1f)) {
                SpecialtyField("Current medications", "medical_meds", minLines = 4)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Diagnosis / Advice", modifier = Modifier.weight(1f)) {
                SpecialtyField("Diagnosis", "medical_diagnosis", minLines = 4)
            }
            SpecialtyCard(title = "Follow Up", modifier = Modifier.weight(1f)) {
                SpecialtyField("Next steps", "medical_followup", minLines = 4)
            }
        }
        SpecialtyCard(title = "Notes") {
            SpecialtyField("Visit notes", "medical_notes", minLines = 5)
        }
    }
}

@Composable
fun NursePlannerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Shift", "Ward / Unit")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyChecklistCard("Medication Tasks", 5, "nurse_meds", Modifier.weight(1f))
            SpecialtyChecklistCard("Patient Checks", 5, "nurse_checks", Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Assessments", modifier = Modifier.weight(1f)) {
                SpecialtyField("Assessment notes", "nurse_assessment", minLines = 4)
            }
            SpecialtyCard(title = "Hand-Off Notes", modifier = Modifier.weight(1f)) {
                SpecialtyField("What should the next shift know?", "nurse_handoff", minLines = 4)
            }
        }
    }
}

@Composable
fun DoctorListBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val rows = listOf("Doctor 1", "Doctor 2", "Doctor 3", "Doctor 4", "Doctor 5")
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Month", "Family Member")
        SpecialtyTable(
            header = listOf("Doctor", "Specialty", "Phone", "Next Visit", "Notes"),
            rows = rows,
            keyPrefix = "doctor_list",
            firstColumnWidth = 104.dp,
            cellWidth = 112.dp,
        )
    }
}

@Composable
fun EventPlannerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Event Name", "Date")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Overview", modifier = Modifier.weight(1f)) {
                SpecialtyField("Theme / venue / attendees", "event_overview", minLines = 4)
            }
            SpecialtyChecklistCard("Checklist", 6, "event_checklist", Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SpecialtyCard(title = "Vendors", modifier = Modifier.weight(1f)) {
                repeat(4) { index -> SpecialtyField("Vendor ${index + 1}", "event_vendor_$index") }
            }
            SpecialtyCard(title = "Timeline", modifier = Modifier.weight(1f)) {
                repeat(4) { index -> SpecialtyField("Milestone ${index + 1}", "event_timeline_$index") }
            }
        }
        SpecialtyCard(title = "Budget / Notes") {
            SpecialtyField("Budget reminders, contacts, and notes", "event_notes", minLines = 5)
        }
    }
}

@Composable
fun OfficeOrganizerBoardSection(
    title: String,
    modifier: Modifier = Modifier,
) {
    val times = listOf("8 AM", "10 AM", "12 PM", "2 PM", "4 PM", "6 PM")
    SectionContainer(title = title, modifier = modifier) {
        SpecialtyHeaderRow("Date", "Office Focus")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                SpecialtyCard(title = "Goals") {
                    repeat(3) { index -> SpecialtyField("Goal ${index + 1}", "office_goal_$index") }
                }
                SpecialtyCard(title = "Calls / Emails") {
                    SpecialtyField("Calls", "office_calls", minLines = 3)
                    SpecialtyField("Emails", "office_emails", minLines = 3)
                }
                SpecialtyCard(title = "Meetings") {
                    SpecialtyField("Meeting notes", "office_meetings", minLines = 3)
                }
            }
            SpecialtyCard(title = "Schedule", modifier = Modifier.weight(1f)) {
                times.forEachIndexed { index, time ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        TimePill(time)
                        SpecialtyField("", "office_schedule_$index", modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun SpecialtyHeaderRow(
    leftLabel: String,
    rightLabel: String,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        SpecialtyField(leftLabel, "header_$leftLabel", modifier = Modifier.weight(1f))
        SpecialtyField(rightLabel, "header_$rightLabel", modifier = Modifier.weight(1f))
    }
}

@Composable
private fun SpecialtyDayScroller(
    days: List<String>,
    keyPrefix: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        days.forEach { day ->
            SpecialtyCard(title = day, modifier = Modifier.width(160.dp)) {
                SpecialtyField("Plan", "${keyPrefix}_${day}_plan", minLines = 3)
                SpecialtyField("Reminders", "${keyPrefix}_${day}_reminders", minLines = 2)
            }
        }
    }
}

@Composable
private fun SpecialtyChecklistCard(
    title: String,
    lines: Int,
    keyPrefix: String,
    modifier: Modifier = Modifier,
) {
    SpecialtyCard(title = title, modifier = modifier) {
        repeat(lines) { index ->
            ChecklistLine(key = "${keyPrefix}_$index")
        }
    }
}

@Composable
private fun SummaryBox(
    title: String,
    key: String,
    modifier: Modifier = Modifier,
) {
    SpecialtyCard(title = title, modifier = modifier) {
        SpecialtyField(title, key)
    }
}

@Composable
private fun SpecialtyMatrix(
    firstHeader: String,
    columns: List<String>,
    rows: List<String>,
    keyPrefix: String,
    firstColumnWidth: Dp,
    cellWidth: Dp,
) {
    SpecialtyTable(
        header = listOf(firstHeader) + columns,
        rows = rows,
        keyPrefix = keyPrefix,
        firstColumnWidth = firstColumnWidth,
        cellWidth = cellWidth,
    )
}

@Composable
private fun SpecialtyTable(
    header: List<String>,
    rows: List<String>,
    keyPrefix: String,
    firstColumnWidth: Dp,
    cellWidth: Dp,
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HeaderPill(header.first(), firstColumnWidth)
                header.drop(1).forEach { HeaderPill(it, cellWidth) }
            }
            rows.forEachIndexed { rowIndex, row ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    HeaderPill(row, firstColumnWidth)
                    repeat(header.size - 1) { cellIndex ->
                        SpecialtyField(
                            "",
                            "${keyPrefix}_${rowIndex}_$cellIndex",
                            modifier = Modifier.width(cellWidth),
                            fillWidth = false,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SpecialtyCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.plan92Palette.fieldSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.plan92Palette.primaryAccent,
                fontWeight = FontWeight.Bold,
            )
            content()
        }
    }
}

@Composable
private fun TimePill(label: String) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.14f),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.plan92Palette.secondaryAccent,
        )
    }
}

@Composable
private fun HeaderPill(
    label: String,
    width: Dp,
) {
    Surface(
        modifier = Modifier.width(width),
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.12f),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.plan92Palette.primaryAccent,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun PriorityProgressRow(
    label: String,
    key: String,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        TimePill(label)
        SpecialtyField("Progress", key, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ToggleDotsRow(
    title: String,
    count: Int,
    keyPrefix: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.plan92Palette.titleColor,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            repeat(count) { index ->
                var active by rememberSaveable("${keyPrefix}_$index") { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .width(22.dp)
                        .height(22.dp)
                        .clip(CircleShape)
                        .background(
                            if (active) MaterialTheme.plan92Palette.secondaryAccent.copy(alpha = 0.22f)
                            else MaterialTheme.plan92Palette.pageSurface,
                        )
                        .clickable { active = !active },
                    contentAlignment = Alignment.Center,
                ) {
                    if (active) {
                        Text("✓", color = MaterialTheme.plan92Palette.secondaryAccent, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    }
}

@Composable
private fun ChecklistLine(
    key: String,
) {
    var checked by rememberSaveable("${key}_checked") { mutableStateOf(false) }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .width(22.dp)
                .height(22.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(
                    if (checked) MaterialTheme.plan92Palette.primaryAccent.copy(alpha = 0.18f)
                    else MaterialTheme.plan92Palette.pageSurface,
                )
                .clickable { checked = !checked },
            contentAlignment = Alignment.Center,
        ) {
            if (checked) {
                Text("✓", color = MaterialTheme.plan92Palette.primaryAccent, style = MaterialTheme.typography.labelMedium)
            }
        }
        SpecialtyField("", "${key}_text", modifier = Modifier.weight(1f))
    }
}

@Composable
private fun SpecialtyField(
    label: String,
    key: String,
    modifier: Modifier = Modifier,
    minLines: Int = 1,
    fillWidth: Boolean = true,
) {
    var value by rememberSaveable(key) { mutableStateOf("") }
    Surface(
        modifier = if (fillWidth) modifier.fillMaxWidth() else modifier,
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.plan92Palette.pageSurface,
        border = BorderStroke(1.dp, MaterialTheme.plan92Palette.lineColor),
    ) {
        BasicTextField(
            value = value,
            onValueChange = { value = it },
            textStyle = TextStyle(
                color = MaterialTheme.plan92Palette.titleColor,
                fontSize = 13.sp,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            minLines = minLines,
            decorationBox = { innerTextField ->
                if (value.isBlank() && label.isNotBlank()) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.plan92Palette.bodyColor.copy(alpha = 0.7f),
                    )
                }
                innerTextField()
            },
        )
    }
}
