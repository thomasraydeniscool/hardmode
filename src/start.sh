#!/bin/sh

DIR="$(dirname "$0")"

FETCH_PAPER="$DIR/fetch-paper.sh"

export PAPER_OUTPUT_PATH="$ENTRYPOINT"

(exec "$FETCH_PAPER")

cd "/src"

java -jar "$ENTRYPOINT"