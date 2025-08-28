# Chuck Norris facts - Basic inline usage

|             |                                                                                               |
|-------------|-----------------------------------------------------------------------------------------------|
| Purpose     | Show how to use comment-based prompts to guide Copilot in inline mode.                        |
| Description | Build a simple script that fetches Chuck Norris facts from an API and displays them in the console. |
| File        | [`inline-demos/facts.sh`](inline-demos/facts.sh)                                              |

**Commands**

```bash
# Make the script executable
chmod +x inline-demos/facts.sh

# Run the script
inline-demos/facts.sh
```

### Fetch Chuck Norris fact from API

```bash
# Fetch a random Chuck Norris fact from the API and display it in the console
```

This should generate a simple curl command to fetch data from [the Chuck Norris API](https://api.chucknorris.io/jokes/random) and use `jq` to parse the JSON response.

```bash
# Ensure that curl is installed
```

This should generate a check to see if `curl` is installed, and if not, print an error message and exit.

```bash
# Ensure that jq is installed
```

Add a similar check for `jq`.

Run the script:

```bash
chmod +x inline-demos/facts.sh
./inline-demos/facts.sh
```

### Refine prompt

Change the curl prompt ensure all required tools are installed before proceeding.

```bash
# Define the required tools in a list and check if each is installed
```

This should generate a loop that iterates over a list of required tools and checks if each is installed, printing an error message and exiting if any are missing.
It should also clean up the previous individual checks for `curl` and `jq`.

### Extend functionality

```bash
# Get a list of available categories from the API
```

```bash
# Get a random Chuck Norris fact from the "dev" category
```

## Another way: Start writing code

```bash
#!/bin/bash

function fetch_chuck_norris_joke() {
    category=$1
    curl -s https://api.chucknorris.io/jokes/random | jq -r '.value'
}

function ensure_dependencies() {
    if ! command -v curl &> /dev/null; then
        echo "curl is not installed. Please install it to use this script."
        exit 1
    fi

    if ! command -v jq &> /dev/null; then
        echo "jq is not installed. Please install it to use this script."
        exit 1
    fi
}

ensure_dependencies
fetch_chuck_norris_joke dev
```
