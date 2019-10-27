#!/bin/sh

#
# How to run this script?
#
# ./fetch-paper PAPER_OUTPUT_PATH=/tmp/server/paper.jar MINECRAFT_VERSION=1.14.4
#

DIR="$(dirname "$0")"

if [ -n "$MINECRAFT_VERSION" ] && [ -n "$PAPER_OUTPUT_PATH" ]; then
    echo "Preparing PaperMC:$MINECRAFT_VERSION at $PAPER_OUTPUT_PATH"
else
    echo "ERROR: Failed to start paper fetch - incorrect environment variables"
    exit 3
fi

PAPER_DEFAULT_DOWNLOAD_URL="https://papermc.io/api/v1/paper/$MINECRAFT_VERSION/latest/download"

if [ ! -f "$PAPER_OUTPUT_PATH" ] || [ -n "$PAPER_FORCE_DOWNLOAD" ]; then
    url="${PAPER_DOWNLOAD_URL:-$PAPER_DEFAULT_DOWNLOAD_URL}"
    
    echo "Downloading PaperMC $MINECRAFT_VERSION from $url ..."
    
    curl --progress-bar -L "$url" -o "$PAPER_OUTPUT_PATH"
    
    if [ ! -f "$PAPER_OUTPUT_PATH" ]; then
      echo "ERROR: Failed to download from $url"
      exit 3
    fi
else
    echo "WARNING: Stopped because $PAPER_OUTPUT_PATH already exists"
    exit 3
fi