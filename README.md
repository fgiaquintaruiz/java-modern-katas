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
├── domain/              shared immutable model for a vehicle-rental business (fleet + pricing)
├── streams/flatmap/     Block A — nested collections and flatMap (A01–A07)
├── streams/grouping/    Block B — groupingBy / partitioningBy / downstream collectors (B01–B07)
├── streams/reduction/   Block C — statistics and reduction (C01–C06)
├── functional/methodref/ Block M — the four shapes of method references (M01–M08)
├── collectors/          custom java.util.stream.Collector implementation
└── optional/            Optional anti-patterns: wrong vs. correct vs. why
```

Naming: `A01`-`A07` (flatMap), `B01`-`B07` (grouping), `C01`-`C06`
(reduction), `M01`-`M08` (method references). Class name `<Id><PascalCaseTitle>`, e.g. `A03CitiesOfferingCategory`.

Each unsolved exercise: a javadoc statement, a method that throws
`UnsupportedOperationException("Not solved yet")`, and a red
`<ClassName>Test`. Once solved it carries an `Internals` javadoc section:
what happens inside the pipeline (laziness, fusion, short-circuiting,
stateful operations, allocations), not what the code does.

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

## Progress tracking

`PROGRESS.md` is gitignored, one entry per session. The questions the
learner could not answer are the real syllabus for the evaluation; they
matter more than the exercises marked done.

## Deliverables for October

- 20 Streams exercises solved and commented (7 flatMap + 7 grouping + 6 reduction).
- One custom `Collector` with tests.
- One Optional anti-pattern block: wrong version, correct version, why the wrong one is wrong.
- Every solved exercise carries an `Internals` comment.
