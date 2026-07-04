# Project 1 — Library System

## Overview

A console-based library management system built in pure Java (no frameworks). This is the first project in a structured Java learning path, focused on core OOP concepts: encapsulation, inheritance, method overriding, and `equals()`/`hashCode()` contracts.

---

## Learning Goals

| Concept | Where it appears |
|---|---|
| Encapsulation | `Book` and `Member` — private fields, controlled access via getters |
| `equals()` / `hashCode()` | `Book` compared by `isbn`, `Member` compared by `memberId` |
| Validation ownership | All business rules live in `Library`, not in `Book` or `Member` |
| Custom exceptions | Five domain-specific `RuntimeException` subclasses |
| Unit testing | JUnit 5 tests covering all happy paths and all failure cases |

---

## Project Structure

```
src/
├── main/java/com/tutorial/
│   ├── App.java                         # Entry point — main method
│   └── library/
│       ├── Book.java                        # Book entity
│       ├── Member.java                      # Member entity
│       ├── Library.java                     # Core logic and validation
│       ├── BookNotFoundException.java       # Thrown when isbn not found
│       ├── BookNotAvailableException.java   # Thrown when book is already borrowed
│       ├── BookNotBorrowedByThisUserException.java  # Thrown on invalid return
│       ├── MemberNotFoundException.java     # Thrown when memberId not found
│       └── MaximumBorrowedBookException.java        # Thrown when member hits 3-book limit
└── test/java/com/tutorial/
    ├── LibraryTest.java                 # Business rule tests
    └── MemberTest.java                  # Member borrow/return mechanics
```

---

## Domain Rules

- A member can borrow a maximum of **3 books** at a time.
- A book can only be borrowed by **one member** at a time.
- A member cannot borrow a book they **already hold**.
- A book can only be returned by the **member who borrowed it**.
- Duplicate members (same `memberId`) are silently ignored on registration.

---

## Classes

### `Book`

Represents a book in the library.

| Field | Type | Description |
|---|---|---|
| `title` | `String` | Book title |
| `author` | `String` | Author name |
| `isbn` | `String` | Unique identifier |
| `available` | `boolean` | Whether the book can be borrowed |

- `equals()` and `hashCode()` are based on `isbn` only — two books with the same isbn are considered the same book regardless of other fields.
- `available` defaults to `true` when `null` is passed to the constructor.

---

### `Member`

Represents a library member.

| Field | Type | Description |
|---|---|---|
| `memberId` | `String` | Unique identifier |
| `name` | `String` | Member name |
| `borrowedBooks` | `List<Book>` | Books currently held |

- `getBorrowedBooks()` returns a **defensive copy** — external code cannot mutate the internal list directly.
- `borrowBook()` and `returnBook()` handle only the mechanics of adding/removing from the list. No validation here — that belongs to `Library`.

---

### `Library`

Central class that owns all business logic and validation.

| Method | Description |
|---|---|
| `addBook(Book)` | Adds a book to the library catalogue |
| `registerMember(Member)` | Registers a member, ignores duplicates |
| `borrowBook(String isbn, String memberId)` | Validates and processes a borrow request |
| `returnBook(String isbn, String memberId)` | Validates and processes a return request |
| `listAvailableBooks()` | Prints all books currently available to borrow |

---

### Exceptions

All exceptions extend `RuntimeException` (unchecked) — they represent business rule violations that are handled at the top level, not at every call site.

| Exception | Thrown when |
|---|---|
| `BookNotFoundException` | No book with the given isbn exists in the library |
| `BookNotAvailableException` | Book exists but is currently borrowed by someone else |
| `BookNotBorrowedByMemberException` | Member tries to return a book they did not borrow |
| `MemberNotFoundException` | No member with the given memberId is registered |
| `MaximumBooksLimitReachedException` | Member already holds 3 books |

---

## Why validation lives in `Library`, not in `Book` or `Member`

`Member` only knows about the books it currently holds. It has no knowledge of what books exist in the library, whether a book is available, or whether another member holds it. `Library` has the full picture — the complete catalogue and all registered members — so it is the only class with enough context to enforce borrowing rules.

Putting validation in `Member` would require `Member` to hold a reference to `Library`, creating a circular dependency that makes both classes impossible to test in isolation.

---

## Running the Tests

```bash
mvn test
```

### Test coverage

**`LibraryTest`**
- Successful borrow — book marked unavailable
- Successful return — book marked available again
- Borrow unavailable book → `BookNotAvailableException`
- Borrow beyond 3-book limit → `MaximumBooksLimitReachedException`
- Borrow with unregistered member → `MemberNotFoundException`
- Return book not borrowed by this member → `BookNotBorrowedByMemberException`
- Borrow non-existent book → `BookNotFoundException`
- Return non-existent book → `BookNotFoundException`

**`MemberTest`**
- Borrow adds book to member's list
- Return removes book from member's list
- Operations on one member do not affect another

---

## Key Java Concepts Practiced

**`equals()` and `hashCode()` contract** — both methods must be overridden together and based on the same field (`isbn` for `Book`, `memberId` for `Member`). If only `equals()` is overridden, objects that are logically equal will behave incorrectly in `HashSet` and `HashMap`.

**Defensive copy** — `getBorrowedBooks()` returns `new ArrayList<>(borrowedBooks)` instead of the real list. This prevents external code from bypassing validation by manipulating the list directly.

**Unchecked exceptions for domain errors** — domain violations (`BookNotFoundException`, etc.) extend `RuntimeException` so they propagate freely without forcing every caller to declare `throws` or write try/catch blocks. A single handler at the top level (in a real app, a Spring `@ControllerAdvice`) catches and maps them to appropriate responses.