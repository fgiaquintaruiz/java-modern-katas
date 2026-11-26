# java-modern-katas

Personal katas repo for a Learning & Development plan on modern Java
(October 2026 to January 2027). Module 1: Streams, Optional, Java 17-25,
SOLID, GoF patterns. The target is not "how to use it" but why it works
this way and when not to use it.

## Working discipline

Three modes for the AI assistant. Mixing them defeats the exercises.

- Exercise mode: the learner solves. The assistant writes only the
  statement, input data, red test and stub signature; no solution, not
  even partial. Stuck: first what was tried and what the error says; a
  hint after two failed attempts; an explanation after three. Before the
  next exercise the learner explains what each operation does
  internally; a vague explanation does not advance.
- Comparison mode: after a solution exists. One or two alternatives,
  compared on readability, allocations, passes over the collection and
  parallel-stream behaviour. Mistakes are called out bluntly.
- Work mode: unrestricted. Scaffolding, configuration, documentation,
  code outside the evaluation (this skeleton was built in work mode).

Governing rule: progress exercises are solved without AI; AI enters
afterwards to compare, question and speed up project work.

## Repository layout

```
src/main/java/com/fgiaquinta/katas/
├── domain/              shared immutable model for a charter-airline business (fleet + pricing)
├── streams/flatmap/     Block A — nested collections and flatMap (A01–A07)
├── streams/grouping/    Block B — groupingBy / partitioningBy / downstream collectors (B01–B07)
├── streams/reduction/   Block C — statistics and reduction (C01–C06)
├── functional/methodref/ Block M — the four shapes of method references (M01–M08)
├── collections/         Block D — one JDK structure per exercise: ArrayDeque, LinkedHashMap, TreeMap, PriorityQueue... (D01–D10)
├── language/            Block E — language internals: final, static, init order, records, sealed, capture, == (E01–E08)
├── realtime/            timed exercises, 25-minute box, own rules in package-info (LruCache)
├── collectors/          custom java.util.stream.Collector implementation
└── optional/            Optional anti-patterns: wrong vs. correct vs. why

src/main/kotlin/com/fgiaquinta/katas/kt/
├── flatmap/             Kotlin twins of A01–A04, redone after the Java version as spaced repetition
├── collections/         Kotlin twins of D01–D10, each with one test aimed at the Kotlin difference
└── language/            Kotlin twins of E01–E08

python/
├── pyproject.toml       Python 3.15 through uv: pytest, ruff and mypy config; `uv sync` creates .venv
├── katas/domain.py      the same charter-airline model as frozen dataclasses and Enums
├── katas/flatmap/       Python twins of A01–A07, one module and one function per exercise
└── tests/flatmap/       the Java tests ported one to one, plain assert

docs/
├── java-kotlin-cheat-sheet.xlsx   one row per mechanism; yellow columns are the learner's own words
├── log/                 one Markdown log per closed exercise: attempts, hints, internals exchange, principle question
└── realtime/            notes per timed exercise (gitignored: scores and how it felt stay local)
```

Naming: `A01`-`A07` (flatMap), `B01`-`B07` (grouping), `C01`-`C06`
(reduction), `M01`-`M08` (method references), `D01`-`D10` (collections),
`E01`-`E08` (language internals). Class name `<Id><PascalCaseTitle>`, e.g. `A03CitiesOfferingCategory`.

Each unsolved exercise: a javadoc statement, a method that throws
`UnsupportedOperationException("Not solved yet")`, and a red
`<ClassName>Test`. Once solved it carries an `Internals` javadoc section:
what happens inside the pipeline (laziness, fusion, short-circuiting,
stateful operations, allocations), not what the code does.

Kotlin twins (`com.fgiaquinta.katas.kt.<block>`) repeat solved Java exercises as top-level functions with the same names, stub `TODO("Not solved yet")` and a ported test; `--tests "com.fgiaquinta.katas.kt.*A01*"` runs the Kotlin one only.

Python twins live in `python/katas/<block>/` with snake_case names (`a01_all_registrations.py`, function `all_registrations`), stub `raise NotImplementedError("Not solved yet")` and a ported pytest test under `python/tests/<block>/`.

## Build and test

JDK 21 toolchain. Java 25 is planned for the November block.

PowerShell:

```powershell
.\gradlew.bat test
.\gradlew.bat test --tests "com.fgiaquinta.katas.streams.flatmap.*"
.\gradlew.bat test --tests "*A01*"
```

Git Bash:

```bash
./gradlew test
./gradlew test --tests "com.fgiaquinta.katas.streams.flatmap.*"
./gradlew test --tests "*A01*"
```

Python twins, from the `python/` directory, same lines in PowerShell and Git Bash
(`uv sync` once, it installs CPython 3.15 and the dev tools into `.venv`):

```bash
cd python; uv sync
uv run pytest -k a01
uv run ruff check .
uv run mypy
```

## Progress tracking

`PROGRESS.md` is gitignored, one entry per session. The questions the
learner could not answer are the real syllabus for the evaluation; they
matter more than the exercises marked done.

Since 2026-11-17 every closed exercise also gets a committed log in
`docs/log/`, written from the conversation: attempts in order, hints
taken, the internals exchange in the learner's words, the principle
question and each miss. It shows where the exercise was solved alone and
where the assistant entered.

## Journal

One line per solved exercise, newest last. "Hints" counts directed
hints; "explained" means the third failed attempt triggered a full
explanation.

| Date | Exercise | What it is about | How it went |
|---|---|---|---|
| 2026-10-29 | A01 AllRegistrations | one-to-many flatten: airports → fleets → registrations | solved alone |
| 2026-10-29 | A02 DistinctFeaturesSorted | two nesting levels, distinct + sorted are stateful | solved alone |
| 2026-10-29 | A03 CitiesOfferingCategory | anyMatch inside filter, Set collapses duplicate cities | solved alone; cold re-ask passed 2026-10-27 |
| 2026-10-29 | A04 FirstElectricRegistration | findFirst short-circuit through flatMap, third airport never read | solved alone |
| 2026-10-29 | Kotlin A01 | eager List ops vs asSequence | comparison questions only |
| 2026-10-30 | A05 CharterExtraLines | one line per extra, charters with no extras contribute nothing | solved alone |
| 2026-11-10 | A06 UpgradeCandidates | per charter, fleet aircraft of a strictly higher tier | one hint |
| 2026-11-10 | Kotlin A02 | distinct + sorted in Kotlin | comparison questions only |
| 2026-11-10 | A07 LargeAircraftWithMapMulti | mapMulti instead of flatMap, no intermediate stream | solved alone |
| 2026-11-11 | B01 CountByCategory | groupingBy + counting; classifier, long[1] box, finisher | explained after three misses; principle: Strategy |
| 2026-11-11 | Kotlin A04 | asSequence + flatMap + firstOrNull laziness | comparison questions only |
| 2026-11-11 | B02 RegistrationsByCity | groupingBy + flatMapping; bucket created before downstream | two misses, then solved; principle: Decorator |
| 2026-11-12 | realtime LruCache | LinkedHashMap access order + removeEldestEntry, 25-minute box | partial on the clock, 0 hints; finished off the clock 2026-11-17; principle: Template Method vs Strategy, composition explained after three misses ([log](docs/log/realtime-lru-cache.md)) |
| 2026-11-17 | Kotlin A03 | `any` on the inner list as short-circuit, `toSet` dedup | first version built two lists per airport, second matched the Java shape ([log](docs/log/kt-flatmap-a03-cities-offering-category.md)) |
| 2026-11-17 | scaffolding | blocks D (collections) and E (language) added in Java and Kotlin, cheat sheet Collections rows, exercise logs | work mode |
| 2026-11-18 | D01 BoardingQueue | ArrayDeque as a stack: push moves the head index, ring buffer, iteration from head gives LIFO | solved alone; subList().reversed() detour reverted; principle: Liskov after one miss ([log](docs/log/collections-d01-boarding-queue.md)) |
| 2026-11-26 | scaffolding | Python twins of all 46 exercises under python/ (pytest, dataclass domain), Python sheet and Collections column in the cheat sheet; daily order becomes Python, Java, Kotlin twin, principles | work mode |

## Deliverables for October

- 20 Streams exercises solved and commented (7 flatMap + 7 grouping + 6 reduction).
- One custom `Collector` with tests.
- One Optional anti-pattern block: wrong version, correct version, why the wrong one is wrong.
- Every solved exercise carries an `Internals` comment.
