#!/bin/sh

#
# How to run this script?
#
# ./fetch-paper PAPER_OUTPUT_PATH=/tmp/server/paper.jar PAPER_TARGET_VERSION=1.14.4
#

DIR="$(dirname "$0")"

if [ -n "$PAPER_TARGET_VERSION" ] && [ -n "$PAPER_OUTPUT_PATH" ]; then
    echo "Preparing PaperMC:$PAPER_TARGET_VERSION at $PAPER_OUTPUT_PATH"
else
    echo "Incorrect environment variables"
    exit 3
fi

PAPER_DEFAULT_DOWNLOAD_URL="https://papermc.io/api/v1/paper/$PAPER_TARGET_VERSION/latest/download"

if [ ! -f "$PAPER_OUTPUT_PATH" ] || [ -n "$PAPER_FORCE_DOWNLOAD" ]; then
    url=$PAPER_DOWNLOAD_URL:-PAPER_DEFAULT_DOWNLOAD_URL
    
    echo "Downloading PaperMC $PAPER_TARGET_VERSION from $url ..."
    
    curl --progress-bar -L "$url" -o "$PAPER_OUTPUT_PATH"
    
    if [ ! -f "$PAPER_OUTPUT_PATH" ]; then
      echo "ERROR: failed to download from $url (status=$?)"
      exit 3
    fi
else
    echo "Stopped because $PAPER_OUTPUT_PATH already exists"
    exit 3
fi