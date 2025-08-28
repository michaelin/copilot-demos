# Ask mode - simple questions

|             |                                                             |
|-------------|-------------------------------------------------------------|
| Purpose     | Show simple usage of Ask mode                               |
| Description | Ask various simple questions and show difference between models  |
| File        | -                                                           |

## Differeces between models

Run the same prompt with different models:

- GPT 4.1
- GTP 5-mini
- Claude Sonnet 4

Each one should run in a fresh Chat instance.

```text
What is the airspeed velocity of an unladen swallow?
```

## Information Cutoff Date

Use GTP4.1 - it guesses wrong at first, but then provides good answer after looking up the answer.

```text
What is the latest version of Python?
```

It will probably show wrong information and reference information that is later than it cutoff date.

Show cutoff date:

```text
What is your information cutoff date?
```

Then retry:

```text
What is the latest version of Python?
```

It should be better now.

## Fetch from web

```text
#fetch What is the latest version of Python?
```

## Information level of different models

Try `#fetch` with different models to see the differences.

## Chat tools

Explain how slash commands work

Show tools / contexts:

```text
#
````

- These are all chat tools that are used to **accomplish specialized tasks** while processing a user request.
- Adds extra information to the context

Also show the [VSCode reference for chat tools](https://code.visualstudio.com/docs/copilot/reference/copilot-vscode-features#_chat-tools)

Show adding a specific file to context

```text
#file:.gitignore Which languages are handled by the current config?
```

Show adding the full codebase as context. First try without

```text
Where can I find the documentation for the Chuck Norris facts demo
```

Then add the `#codebase` context

```text
#codebase Where can I find the documentation for the Chuck Norris facts demo
```

## Chat Participants and Slash Commands

```text
/help
```

Explain Chat Participants - handle domain-specific requests in chat. Adds slash commands that you can use.

Slash commands are **shortcuts** to specific functionality within the chat. You can use them to quickly perform actions, like **fixing issues**, **generating tests**, or **explaining code**.

Example:

```text
@workspace /explain the different sorting algorithms in #file:AdvancedSorting.java
````

This should give a detailed explanation of each algorithm.

## Extensions

New `@ChatParticipants` and `#tools` can be added using extensions. e.g. `Azure`

Each one has their own slash-commands

Show [GitHub Marketplace](https://github.com/marketplace?type=apps&copilot_app=true)
