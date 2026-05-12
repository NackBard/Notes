# 📝 Notes

An Android notes app with a home screen widget, built with **Kotlin**. Uses the MVVM architecture pattern with Data Binding, a local SQLite database, and a `RemoteViewsService`-powered list widget that displays pinned notes directly on the home screen.

## ✨ Features

- 📋 **Notes list** — view all saved notes in a scrollable ListView
- ➕ **Add notes** — create new notes with optional "pinned" checkbox
- 📌 **Home screen widget** — displays pinned notes as a live list on the home screen, with a refresh button
- 🗄️ **Local persistence** — all notes saved to SQLite via a custom `SQLiteOpenHelper`
- 🔄 **Auto-refresh** — the main list updates on return from the add-note screen via `onRestart()`

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| Architecture | MVVM (ViewModel + Data Binding) |
| Database | SQLite (`SQLiteOpenHelper`) |
| UI | ListView + BaseAdapter, RemoteViews (widget) |
| Widget | `AppWidgetProvider` + `RemoteViewsService` |
| Data Binding | Android Data Binding Library (kapt) |
| Min SDK | API 16 (Android 4.1+) |
| Target SDK | API 30 (Android 11) |

## 🏗️ Architecture

The project is structured around the **MVVM pattern**, with a clear separation between UI, business logic, and data layers:

```
app/src/main/java/com/example/notes/
├── MainActivity.kt               # Notes list screen
├── AddNote.kt                    # Add note screen (binds to DataViewModel)
├── ListViewAppWidget.kt          # Home screen widget (AppWidgetProvider)
├── Services.kt                   # RemoteViewsService for widget list data
├── ResultCallBack.kt             # Callback interface (success/error events)
│
├── adapter/
│   ├── ListViewAdapter.kt        # BaseAdapter for in-app notes list
│   └── ListViewAdapterWidget.kt  # RemoteViewsFactory for widget list
│
├── model/
│   ├── Data.kt                   # Note model (BaseObservable for binding)
│   └── DatabaseHelper.kt         # SQLite CRUD: insert, selectAll, selectPinned
│
└── viewmodel/
    ├── DataViewModel.kt          # Business logic: TextWatcher, checkbox, save
    └── DataViewModelFactory.kt   # Custom ViewModelProvider.Factory
```

## 🔑 How It Works

**MVVM + Data Binding** — `AddNote` binds directly to `DataViewModel` via XML layout. The `TextWatcher` in the ViewModel updates the `Data` model in real time as the user types, and `onAddClicked` validates and persists the note to SQLite.

**SQLite with two queries** — `DatabaseHelper` provides `select()` (all notes) for the main app and `selectTrue()` (pinned notes only, `CHECKED = 1`) for the widget, keeping the home screen clutter-free.

**Widget with RemoteViewsService** — `ListViewAppWidget` uses `setRemoteAdapter()` to populate a `ListView` inside the widget. Data is fetched by `MyService` (a `RemoteViewsService`) which returns a `ListViewAdapterWidget` — the correct pattern for scrollable lists in Android widgets.

**Callback interface** — `ResultCallBack` decouples the ViewModel from the Activity, allowing `DataViewModel` to report success/error without holding a direct Activity reference.

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or newer
- Android device or emulator (API 16+)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/NackBard/Notes.git
   ```

2. Open in Android Studio and run on a device or emulator.

3. To use the widget: long-press the home screen → **Widgets** → find **Notes** → drag to home screen. Only notes marked as **pinned** (checkbox checked) will appear in the widget.

---

> Built with ❤️ using Kotlin, MVVM, SQLite and Android AppWidget API
