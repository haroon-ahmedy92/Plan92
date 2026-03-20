# Template Implementation Tracker

This file audits the screenshots in `pictures/` and turns them into a reusable implementation roadmap for the Compose planner engine.

Status legend:
- `Engine family mapped`: the current template engine can already render this as an editable generic family.
- `Variant needs modeling`: the family exists, but this screenshot shows a distinct variant that still needs a dedicated definition.
- `Backlog - add template ID`: visible in screenshots, but not yet represented as a stable template entry.
- `Cover only`: visible as a book/cover asset; not yet represented as an editable interior page.

Editable legend:
- `Yes - generic`: editable now through the current engine family.
- `Partial`: editable in spirit, but not yet matching the screenshot structure closely.
- `No`: not yet routed into the editor as its own template.

## 1. Major Screen Types Found

| Screen Type | Screenshot Evidence | Notes |
|---|---|---|
| Splash / launch | `110355` | Coral launch screen with centered brand mark and orbit motifs. |
| Reminder onboarding | `110400` | Empty-state onboarding with illustration and CTA. |
| Reminder schedule | `110428` | Frequency radios, toggle, and inline wheel-style time picker. |
| Home / Your Planners | `110519`, `110540`, `110550`, `110556` | Create tile, owned covers/pages, ready-to-use row, bottom nav shell. |
| Widget promo modal | `110512` | Modal card layered over home. |
| Calendar / reminders empty state | `112306` | Date strip, empty reminder state, FAB. |
| Create New modal | `112313` | Blank page / Book / Ready-to-Use / Import PDF cards. |
| Search | `112326`, `112331` | Search bar + long popular tags list. |
| Templates browser | `112349`, `112401`, `112408`, `112413` | Chip categories, stacked template cards, favorites heart. |
| Favorites empty modal | `112458` | Empty-state illustration and add-favorites CTA. |
| Settings | `112428` | Grouped settings cards, toggles, promo banner, social links. |

## 2. Repeated Layout Patterns Seen Across Templates

### Repeated planner page structures
- Single-column planner sheets with header, date line, and stacked boxed sections.
- Two-column daily pages with left checklist/task lane and right schedule/notes lane.
- Weekly pages using seven day columns or seven day rows.
- Calendar pages using month grids or appointment grids.
- Tracker pages using circles, cups, stars, or checkbox matrices.
- Project/work sheets using overview fields, milestones, action tables, and notes.
- List planners using categorized columns, shopping departments, or packing categories.
- Journal/reflection pages using prompt blocks and lined writing areas.
- Budget pages using repeated expense tables with budget vs actual columns.
- Cover/book pages using decorative full-bleed covers instead of editable interior layouts.

### Repeated interactive patterns
- Checkbox lists and task circles.
- Hour-by-hour schedules.
- Day-of-week markers.
- Water trackers with cup icons.
- Mood selectors with emoji faces.
- Rating rows with stars or circles.
- Mini stat blocks and progress trackers.
- Category columns and sectioned tables.

### Repeated decorative patterns
- Soft pastel title bands.
- Washi-tape labels and banner ribbons.
- Floral / doodle corners.
- Rounded paper cards with subtle shadows.
- Illustrated book covers and stitched leather/notebook covers.
- Tiny icons placed in corners to signal category or mood.

## 3. Reusable Family Classification

| Family | Templates Found In Screenshots | Common Structure | Reusable Primitives Required | Special Sections / Differences | Recommended Order |
|---|---|---|---|---|---|
| Daily planners | Daily Planner variants, Daily Goal Planner, Daily Task Planner, Today's Agenda, Productive Day Planner, Daily Work Planner, Full-Day Hourly Planner, Work Day Organizer Planner, Top 3 Planner, Daily Office Organizer Planner | Header + date + priorities + tasks + schedule + notes | `PlannerTitleBlock`, `DateFieldRow`, `ChecklistSection`, `ScheduleSection`, `HourlyScheduleSection`, `NotesSection`, `WaterTrackerSection`, `PlannerStatBox` | Some variants add weather, money, meals, calls, expenses, or "urgent" blocks | 1 |
| Weekly planners | Weekly Schedule Planner, Weekly Work Schedule Planner, Weekly Dashboard, Weekly Goal Planner, Weekly Goals Plan, Mom Chores Planner, Organized Weekly Bullet Journal | Seven-day layout, day lanes, weekly goals, weekly notes | `DayColumnsSection`, `WeekGridSection`, `HabitTrackerSection`, `NotesSection`, `ChecklistSection`, `ProgressTimelineSection` | Some weekly pages use a path/timeline shape instead of standard columns | 2 |
| Monthly planners | Monthly Planner, Monthly Appointment Planner | Calendar grid with month header and notes | `MonthGridSection`, `DateFieldRow`, `NotesSection` | Appointment page uses lighter decorative frame and ring-bound header | 3 |
| Yearly planners | Seasonal 2026 Yearly Planner, 2026 Yearly Calendar Planner | Cover-first yearly products and year-at-a-glance interiors | `MonthGridSection`, cover components, yearly overview sections | Cover-heavy identity is part of the family, not just the card artwork | 9 |
| Journals / reflection | Daily Journal variants, Feelings Journal, Daily Reflection, Daily Reflection Journal, Journal Prompts for Reflecting on Your Day, Daily Manifest Journal, Daily Thoughts Dear Diary | Prompt blocks, reflection fields, lined writing sections | `JournalPromptSection`, `ReflectionPromptSection`, `MoodSelectorSection`, `NotesSection`, `ChecklistSection`, `WaterTrackerSection` | Some are cover-only diary products, some are full reflective worksheets | 4 |
| Work planners | Work Tasks To Do List, Daily Work Planner, Work Life Balance Planner, Work Day Organizer Planner, Daily Office Organizer Planner | Work buckets, meetings, priorities, schedule, reminders | `ChecklistSection`, `ScheduleSection`, `EditableTextSection`, `NotesSection`, `PlannerStatBox` | Several variants segment by work/personal, calls/emails/meetings, or must-do vs if-time | 5 |
| Project planners | Project Planner, Project Progress Overview, Weekly Projects Work Journal, Task Breakdown Planner, Task Batching Planner | Overview block + milestones + action rows + notes | `EditableTextSection`, `ProgressTimelineSection`, `ChecklistSection`, `NotesSection`, `BudgetGridSection` | Task breakdown uses named blocks; batching uses four categorized quadrants | 5 |
| Budget planners | Vacation Budget Planner, Monthly Budget Overview, Monthly Bill Payment Tracker | Repeated tables with plan/actual columns | `BudgetGridSection`, `TrackerSection`, `NotesSection`, `PlannerStatBox` | Bill payment is more ledger-like than monthly category budgeting | 6 |
| Meal planners | Weekly Meal Planner, High-Protein Weekly Meal Planner, Recipe Journal | Meal-by-day matrix and prep notes | `MealPlannerSection`, `ChecklistSection`, `NotesSection`, `EditableTextSection` | Recipe Journal needs ingredients + method + meta fields rather than week matrix | 6 |
| Grocery / shopping planners | Grocery Planner, Shopping To Do List, Shopping List, Groceries Packing Checklist, Travel Packing List | Categorized lists in columns or day/category splits | `ChecklistSection`, `EditableTextSection`, category-column primitive, `NotesSection` | Travel list mixes packing categories with travel notes and trip metadata | 6 |
| Cleaning / chores planners | Daily Cleaning List, Mom Chores Planner | Room/area grids and day-based chore splits | `ChecklistSection`, `DayColumnsSection`, room-grid primitive | Some are room-based, others are weekly mom/home responsibility split | 6 |
| Habit / tracker planners | My Weekly Habits, 75 Hard Challenge, Monthly Weight Loss Planner | Repeated day matrix or numeric progress tracking | `HabitTrackerSection`, `TrackerSection`, `WaterTrackerSection`, rating/milestone primitives | 75 Hard is a dense challenge matrix; weight loss is month-by-month goal/actual grid | 7 |
| Self-care / wellness planners | Self Care Planner, Self-Care Journal, Routine Planner, Daily Exercise Planner, Daily Devotional Planner | Wellness checklist + hydration + sleep + mood + notes | `ChecklistSection`, `WaterTrackerSection`, `MoodSelectorSection`, `TrackerSection`, `ReflectionPromptSection` | Devotional and self-care journal variants lean more reflective than tracker-like | 7 |
| ADHD planners | ADHD Daily Planner, ADHD Planner checklist variant, ADHD Planner priority/schedule variant | Focus block + routines + decomposed tasks + calm/support widgets | `ChecklistSection`, `ScheduleSection`, `MoodSelectorSection`, `TrackerSection`, `EditableTextSection` | One variant is checklist/wellness heavy; another is priority + reward + schedule heavy | 7 |
| Notes / bullet / list planners | Notes, Daily Bullet Journal, Organized Weekly Bullet Journal, To Do List | Open writing, dotted/bullet layouts, urgent/non-urgent list splits | `NotesSection`, `ChecklistSection`, `HourlyScheduleSection`, `DayColumnsSection` | Bullet pages need more whitespace and sparse framing than standard planners | 4 |
| Family / mom planners | Mom Planner, Mom Chores Planner | Family-focused daily/weekly management with habits, finance, meals | `TrackerSection`, `MealPlannerSection`, `ChecklistSection`, `BudgetGridSection`, `DayColumnsSection` | Mom Planner combines hydration, finances, meals, habits, and savings in one page | 8 |
| Cover / book-style planners | 2026 All-in-One Productivity Planner, Find Your Balance Journal, Organize Your Life with a Bullet Journal, Seasonal 2026 Yearly Planner, 2026 Yearly Calendar Planner, Daily Thoughts Dear Diary, 2026 Weekly Plan Diary, Journal Prompts for Reflecting on Your Day | Full-cover hero artwork, stitched books, decorative spines, typography labels | Cover primitive, book spine primitive, decorative cover card surface | These should coexist with editable interior templates, not replace them | 8 |
| Reminder / calendar / support flows | Reminder onboarding, Schedule reminders, Calendar empty state, widget promo, search, settings, favorites, create-new | Modal cards, list settings, empty states, prompt screens | Existing app-shell primitives, CTA cards, settings rows, empty-state containers | Support screens are mostly complete structurally and need visual polish more than schema work | 10 |

## 4. Template Tracker

### Daily planners

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Daily Goal Planner | Daily planners | `110707`, `110656` | Implemented | Yes | Editable goal, breakdown, to-do, schedule, and notes layout is now routed through a dedicated engine board | Refine top banner proportions and goal-card ornament accents |
| Daily Planner (weather/money/water/mood) | Daily planners | `112349`, `112401`, `112408`, `112413` | Implemented | Yes | Editable weather, priorities, schedule, to-do, money, comment, water, and mood sections now mirror the core screenshot composition | Add icon-style weather chips and tune footer spacing |
| Daily Planner (priorities/schedule/meals/calls) | Daily planners | `112349` | Engine family mapped | Partial | Needs dates checklist, activity row, meals, calls/email text | Create structured sub-variant |
| Daily Planner (timeline + life/goals/appointments) | Daily planners | `112413` | Backlog - add template ID | No | Distinct split layout with life/goals/appointments footer | Add template ID and schema |
| Daily Task Planner | Daily planners | `110751` | Implemented | Yes | Editable urgent, priorities, expenses, notes, and task-list structure now exists as a dedicated board | Tighten box proportions and label hierarchy |
| Today's Agenda | Daily planners | `111025` | Implemented | Yes | Top priorities, agenda column, to-do list, meals, and water areas are now editable in a dedicated layout | Refine meal-card sizing and agenda header styling |
| The Productive Day Planner | Daily planners | `110815` | Implemented | Yes | Editable main-task, appointment, pomodoro, notes, must-do, can-wait, and tomorrow blocks are now routed through the engine | Improve circular tracker fidelity and decorative dividers |
| Full-Day Hourly Planner | Daily planners | `111036` | Implemented | Yes | Editable 5 AM to 4 AM split timeline is now rendered as a dedicated full-day hourly board | Fine-tune row density and time-label spacing |
| Daily Work Planner | Daily planners | `111029` | Implemented | Yes | Editable priorities, to-do, meetings, reminders, and upcoming projects now match the screenshot structure closely | Add stronger weekday-toggle styling and notebook details |
| Work Day Organizer Planner | Daily planners | `111212` | Backlog - add template ID | No | Multi-box organizer with to-do, priorities, deadlines, schedule, calls, emails, meetings | Add template entry and schema |
| Top 3 Planner | Daily planners | `111242` | Backlog - add template ID | No | Three giant focus cards plus tasks and notes | Add top-3 variant |
| Daily Office Organizer Planner | Daily planners | `111242` | Backlog - add template ID | No | Goals/calls/emails left with full-day schedule right | Add office organizer variant |
| Daily Brain Dump | Daily planners | `111242` | Implemented | Yes | Editable brain-dump board now includes to-do, mood, priorities, goals, and can-wait sections | Refine motivational footer and column balance |

### Weekly planners

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Weekly Planner | Weekly planners | `110707`, `110713`, weekly home/template cards | Implemented | Yes | Editable week-at-a-glance planner now includes appointments, wins, day cards, habits, and notes | Refine top summary proportions and day-card ornament details |
| Weekly Schedule Planner | Weekly planners | `111056` | Implemented | Yes | Editable weekly timetable grid with notes strip now renders through a dedicated schedule board | Tighten matrix density and add closer time-grid styling |
| Weekly Work Schedule Planner | Weekly planners | `111242` | Backlog - add template ID | No | Seven vertical columns with goal footer row | Add work-week schema |
| Weekly Dashboard | Weekly planners | `111242` | Implemented | Yes | Editable highlights, top goals, per-day goal cards, mood tracker, and notes now render in a dedicated dashboard board | Refine heart-tracker visuals and section ornamentation |
| Weekly Goals Plan | Weekly planners | `111242` | Implemented | Yes | Editable priorities, goals, daily appointment blocks, weekly notes, and next-week preview now render in a dedicated weekly-goals board | Closer path-like daily composition still needed |
| Weekly Goal Planner | Weekly planners | `111242` | Backlog - add template ID | No | Visible only partially; family confirmed | Capture lower structure when implementing |
| Mom Chores Planner | Weekly planners | `110909` | Backlog - add template ID | No | Day cards with must-do and extra time tasks | Add family-mom weekly chores variant |
| The Organized Weekly Bullet Journal | Weekly planners | `110648` | Implemented | Yes | Editable asymmetrical weekly bullet spread with gratitude, favorites, notes, and next-week blocks is now routed through the engine | Refine asymmetry and decorative divider treatment |
| Weekly Projects Work Journal | Weekly planners | `111151` | Implemented | Yes | Editable weekly work goals, projects, activities, challenges, highlights, and next steps now render through a dedicated board | Tune work-journal proportions and badge styling |

### Monthly and yearly planners

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Monthly Planner | Monthly planners | `110733` | Implemented | Yes | Editable month grid, goals, and important dates now render through a dedicated monthly board | Refine corner accents and grid proportions |
| Monthly Appointment Planner | Monthly planners | `111118` | Implemented | Yes | Editable appointment calendar now includes date-focused cells plus appointment and follow-up sections | Add closer ring-bound and date-circle styling |
| 2026 Yearly Planner | Yearly planners | yearly search/taxonomy and yearly card set | Implemented | Yes | Editable yearly goals and month-by-month overview cards are now available through a dedicated yearly planner template | Refine yearly cover pairing and month-card ornament accents |
| 2026 Yearly Calendar Planner | Yearly planners | `110634`, `110642` | Implemented | Yes | Editable year-at-a-glance monthly summary cards now render through a dedicated yearly-calendar board | Add closer mini-calendar styling and cover pairing |
| Seasonal 2026 Yearly Planner | Yearly planners | `110623`, `110634` | Implemented | Yes | Editable seasonal focus cards and month summaries now render through a dedicated seasonal yearly board | Refine seasonal illustration treatment and quarter framing |

### Journals / reflection / devotional

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Daily Journal (blue reflective) | Journals / reflection | `110550`, `110556` | Engine family mapped | Partial | Needs to-do, reflection questions, priorities, productivity legend | Add reflective journal variant |
| Daily Journal (gratitude/affirmations/mood) | Journals / reflection | `110613` | Engine family mapped | Partial | Needs gratitude, affirmations, for tomorrow, mood list | Add gratitude journal variant |
| My Daily Journal | Journals / reflection | `110830`, `110837` | Cover only | No | Cover-style simple lined diary card | Add diary-cover template |
| Feelings Journal | Journals / reflection | `110556`, `110607` | Engine family mapped | Partial | Needs feeling sidebar, self-love, gratitude, rate today, water row | Add feelings-specific schema |
| Daily Reflection | Journals / reflection | `111137`, `111151` | Implemented | Yes | Editable gratitude, affirmation, achieved/not-achieved, review, and tomorrow-plan blocks are now in a dedicated board | Refine inner card spacing and prompt typography |
| Daily Reflection Journal | Journals / reflection | `111018` | Implemented | Yes | Guided coaching prompts are now editable through a dedicated reflection-journal board | Add closer banner styling and prompt-line density |
| Journal Prompts for Reflecting on Your Day | Journals / reflection | `110607`, `110613` | Cover only | No | Cover/book asset, not interior page | Add cover asset type |
| Daily Manifest Journal | Journals / reflection | `111151` | Implemented | Yes | Editable journaling, gratitude, affirmation, and visualization sections are now rendered in a dedicated manifest board | Refine ornamental accents and text-area proportions |
| Daily Devotional Planner | Journals / reflection | `111125` | Implemented | Yes | Editable prayer, answered prayers, scripture, observation, application, and notes sections now match the screenshot structure closely | Add more devotional-specific decorative accents |
| SOAP Bible Study | Journals / reflection | `111005`, `111012` | Engine family mapped | Partial | Needs scripture/observe/application/prayer quadrants | Add SOAP-specific schema |
| Daily Thoughts Dear Diary | Journals / reflection | `110847`, `110853` | Cover only | No | Decorative diary cover | Add diary cover asset |

### Work / project planners

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Work Tasks To Do List | Work planners | `110837`, `110842` | Implemented | Yes | Editable six-bucket work-task board now supports must-do, if-time, projects, meetings, morning, and afternoon planning | Refine icon accents and bucket spacing |
| Work Life Balance Planner | Work planners | `110800`, `110808` | Implemented | Yes | Editable work vs personal weekly split with wins and reset notes now renders through a dedicated board | Improve notebook-spread feel and weekday header styling |
| Project Planner | Project planners | `110909`, `110916` | Engine family mapped | Partial | Needs objective, start/due/completed, brainstorm box, progress tracker | Add distinct project-form schema |
| Project Progress Overview | Project planners | `111044`, `111049` | Engine family mapped | Partial | Needs repeated mini-project cards with priority and timeline cells | Add repeated project-card primitive |
| Task Batching Planner | Project planners | `110956`, `111001` | Implemented | Yes | Editable four-quadrant batching board now matches the screenshot structure closely | Refine decorative banner and internal row density |
| Task Breakdown Planner | Project planners | `111001`, `111005` | Implemented | Yes | Editable identification, prioritization, estimation, assignment, and conclusion blocks are now routed through the engine | Refine star/banner ornament styling |
| Daily Task Management Guide | Project planners | `110746`, `110751` | Implemented | Yes | Editable quadrant matrix now supports do-first, plan, delegate, and eliminate task entry | Refine matrix heading treatment and quadrant proportions |
| Meeting Note-Taking | Project planners | `110950`, `110956` | Implemented | Yes | Editable meeting metadata and repeated topic note blocks are implemented through a dedicated engine section | Add torn-paper decorative accents and tighter topic spacing |
| Goals Tracker | Project planners | `110935`, `110950` | Implemented | Yes | Added as a dedicated template definition with editable goals, to-do, step, and reward areas | Refine staircase geometry and reward row visuals |

### Budget / finance planners

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Vacation Budget Planner | Budget planners | `111018`, `111025` | Engine family mapped | Partial | Needs category summary and notes grid | Add travel-budget variant |
| Monthly Budget Overview | Budget planners | `111025`, `111029` | Implemented | Yes | Editable budget snapshot, category table, and notes now render through a dedicated monthly budget board | Refine spread proportions and money-stat styling |
| Monthly Bill Payment Tracker | Budget planners | `111212` | Backlog - add template ID | No | Ledger with paid, due date, paid date, amount due/paid, balance, notes | Add bill-tracker template |
| Mom Planner savings area | Budget planners | `110656` | Variant needs modeling | Partial | Embedded savings widget inside family planner | Add embedded stat block for mom planner |

### Meal / grocery / shopping planners

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Weekly Meal Planner | Meal planners | `110707`, `110713` | Implemented | Yes | Editable weekly meal grid with grocery list and prep notes now renders through a dedicated meal-planner board | Refine servings/protein accents and tighter meal-cell spacing |
| High-Protein Weekly Meal Planner | Meal planners | `110916`, `110922` | Backlog - add template ID | No | Simpler protein-focused weekly grid | Add high-protein meal variant |
| Recipe Journal | Meal planners | `111125`, `111137` | Backlog - add template ID | No | Ingredient metadata + method large area + tips block | Add recipe template |
| Grocery Checklist | Grocery / shopping | `111151` and grocery checklist taxonomy | Implemented | Yes | Editable categorized grocery checklist is implemented as a reusable checkable category grid | Add optional snack/household overflow categories and tighter cell heights |
| Grocery Planner | Grocery / shopping | `110742`, `110746` | Implemented | Yes | Editable week-of header, weekday planning stack, and grocery list column are now in the engine | Refine card proportions to match screenshot more tightly |
| Shopping To Do List | Grocery / shopping | `110820`, `110830` | Implemented | Yes | Added as a stable template ID with editable department checklist cells | Fine-tune category count, icon accents, and row density |
| Shopping List | Grocery / shopping | `110927`, `110935` | Implemented | Yes | Editable 3x3 department grid now exists as a reusable category-grid variant | Add lighter title styling and softer card edge treatment |
| Groceries Packing Checklist | Grocery / shopping | `111151` | Backlog - add template ID | No | Multi-category packing checklist with snack section and don't-forget area | Add groceries-packing variant |
| Travel Packing List | Grocery / shopping | `111212` | Engine family mapped | Partial | Needs documents/clothes/toiletries/electronics/financial/medical/etc grid | Add exact packing-list schema |

### Cleaning / chores / home / family

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Daily Cleaning List | Cleaning / chores planners | `110723`, `110733` | Implemented | Yes | Editable room-grid checklist is now implemented with reusable categorized cells | Refine sticker/decor icon treatment and compact spacing |
| Mom Planner | Family / mom planners | `110648`, `110656` | Backlog - add template ID | No | Hybrid daily family page with water, finances, exercise, meals, savings, mood, habits | Add mom-planner template |
| Mom Chores Planner | Family / mom planners | `110901`, `110909` | Backlog - add template ID | No | Weekly chores split per day with must-do / extra time tasks | Add mom-chores template |
| Family organizer implied by search tags | Family / mom planners | `112326`, `112331` | Variant needs modeling | No | Search taxonomy exists, explicit card not shown | Add later if surfaced in cards |

### Wellness / tracker / ADHD

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Self Care Planner | Self-care / wellness planners | `110713`, `110723` | Implemented | Yes | Editable checklist, workout, sleep, water, mood, and happiness-note sections now render in a dedicated board | Refine tracker iconography and card sizing |
| Self-Care Journal | Self-care / wellness planners | `111151` | Backlog - add template ID | No | Weekly reflective self-care prompts by weekday | Add self-care-journal variant |
| Routine Planner | Self-care / wellness planners | `110713` | Implemented | Yes | Editable morning, afternoon, and evening routine cards now sit alongside priorities and to-dos in a dedicated routine board | Improve time-card ornament styling and internal line rhythm |
| Daily Exercise Planner | Self-care / wellness planners | `111056`, `111103` | Implemented | Yes | Editable food, calories, hydration, sleep, focus, workout log, and daily stats are now rendered in a dedicated exercise board | Add vitamins row and closer table styling |
| Monthly Weight Loss Planner | Habit / tracker planners | `111103`, `111110` | Implemented | Yes | Editable month-by-month goal, actual, and notes cards now render through a dedicated progress board | Refine numeric tracker styling and month-card density |
| My Weekly Habits | Habit / tracker planners | `110922`, `110927` | Implemented | Yes | Editable goals, notes & reminders, habit grid, and weekly self-assessment now render through a dedicated weekly-habits board | Add closer iconography and self-assessment ornament details |
| 75 Hard Challenge | Habit / tracker planners | `111110`, `111118` | Backlog - add template ID | No | 75-day dense challenge matrix with rule checklist | Add challenge tracker template |
| ADHD Daily Planner | ADHD planners | `110642`, `110648` | Implemented | Yes | Editable focus, reminders, action buckets, routine, schedule, to-do, and brain-dump sections now render in a dedicated ADHD daily board | Refine reward/reminder ornament details and row density |
| ADHD Planner (checklist / water / sleep) | ADHD planners | `111212` | Backlog - add template ID | No | Self-care-heavy ADHD wellness sheet | Add second ADHD variant |
| ADHD Planner (priority / rewards / schedule) | ADHD planners | `111242` | Backlog - add template ID | No | Priority, daily schedule, rewards, productivity rating | Add third ADHD variant |

### Notes / bullet / open writing

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| Notes | Notes / bullet planners | `110842`, `110847` | Implemented | Yes | Editable lined notes page with dedicated writing surface is now routed through the engine | Refine quote-corner ornament treatment and page margins |
| Daily Bullet Journal | Notes / bullet planners | `111242` | Backlog - add template ID | No | Open spread with hourly edges and blank bullet center | Add bullet-day spread primitive |
| Organized Weekly Bullet Journal | Notes / bullet planners | `110648`, `110656` | Implemented | Yes | Editable multi-box weekly bullet spread with gratitude, favorites, notes, and next-week sections now routes through the engine | Refine asymmetrical spacing and decorative corner accents |
| To Do List | Notes / bullet planners | `111242` | Backlog - add template ID | No | Urgent vs non-urgent split list | Add to-do split variant |

### Covers / book-style products

| Template | Family | Source Screenshot(s) | Implementation Status | Editable Status | Fidelity Notes | Pending Work |
|---|---|---|---|---|---|---|
| 2026 All-in-One Productivity Planner | Cover / book-style planners | `110519` | Cover only | No | Hero cover on home screen | Add cover template + linked interior |
| Find Your Balance Journal | Cover / book-style planners | `110540` | Cover only | No | Stacked-stones cover | Add cover asset |
| Organize Your Life with a Bullet Journal | Cover / book-style planners | `110550` | Cover only | No | Blue book cover | Add cover asset |
| Daily Thoughts Dear Diary | Cover / book-style planners | `110847`, `110853` | Cover only | No | Floral green diary cover | Add cover asset |
| 2026 Weekly Plan Diary | Cover / book-style planners | `110853`, `110901` | Cover only | No | Leather-style diary cover | Add cover asset |
| Journal Prompts for Reflecting on Your Day | Cover / book-style planners | `110607`, `110613` | Cover only | No | Night-sky journal cover | Add cover asset |
| Seasonal 2026 Yearly Planner | Cover / book-style planners | `110623`, `110634` | Cover only | No | Seasonal illustration cover | Pair with yearly interior |
| 2026 Yearly Calendar Planner | Cover / book-style planners | `110634`, `110642` | Cover only | No | Abstract geometric cover | Pair with yearly interior |

## 5. Recommended Implementation Order

1. Daily planner family
   - Highest screenshot volume and highest reuse across the app.
   - Unlocks `Daily Planner`, `Daily Goal Planner`, `Today's Agenda`, `Daily Task Planner`, `Productive Day Planner`, `Daily Work Planner`.

2. Weekly and month-grid families
   - Reusable structures for `Weekly Schedule Planner`, `Weekly Dashboard`, `Weekly Goal(s)`, `Monthly Planner`, and `Monthly Appointment Planner`.

3. Journal / reflection family
   - Covers `Daily Journal`, `Feelings Journal`, `Daily Reflection`, `Daily Reflection Journal`, `SOAP Bible Study`, `Daily Manifest Journal`, `Daily Devotional Planner`.

4. List / shopping / grocery family
   - Shared categorized-column pattern for `Shopping List`, `Shopping To Do List`, `Grocery Planner`, `Groceries Packing Checklist`, `Travel Packing List`.

5. Work / project family
   - Shared form blocks, timelines, and multi-bucket lists for `Project Planner`, `Project Progress Overview`, `Task Breakdown`, `Task Batching`, `Meeting Note-Taking`, `Work Tasks`.

6. Tracker / wellness / ADHD family
   - Shared water, mood, sleep, workout, habit, and checklist sections for `Self Care`, `Routine`, `Exercise`, `Weight Loss`, `75 Hard`, and ADHD variants.

7. Budget / finance family
   - Table-heavy pages can be standardized after tracker grids are stable.

8. Cover / book-style family
   - Needed for polish and library fidelity after editable interiors are solid.

9. Support and shell flows
   - Mostly implemented already; continue polishing after planner page fidelity improves.

## 6. Notes For The Next Phase

- The screenshots clearly support a **family-first implementation** rather than page-by-page ad hoc files.
- The highest-leverage missing engine additions are:
  - room/category grid primitive
  - week-time-grid primitive
  - cover/book primitive
  - progress/milestone card primitive
  - urgent vs non-urgent split list primitive
  - multi-department shopping grid primitive
  - path-style weekly goals primitive
- Several visible templates are **variants of existing families**, not fully new families. Those should get their own `PlannerTemplateDefinition` entries without duplicating primitive logic.
