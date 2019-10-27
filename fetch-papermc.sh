#!/bin/sh

#
# How to run this script?
#
# ./fetch-papermc TARGET_VERSION=1.14.4
#

DIR="$(dirname "$0")"

if [ -n "${TARGET_VERSION}" ]; then
    echo "Preparing PaperMC download - target version ${TARGET_VERSION}"
else
    echo "No target version specified"
    exit 3
fi

DEFAULT_PAPER_DOWNLOAD_URL="https://papermc.io/api/v1/paper/${TARGET_VERSION}/latest/download"

OUTPUT_PATH="${DIR}/dist/paper.jar"

if [ ! -f "${OUTPUT_PATH}" ] || [ -n "$FORCE_DOWNLOAD" ]; then
    downloadUrl=${PAPER_DOWNLOAD_URL:-DEFAULT_PAPER_DOWNLOAD_URL}
    
    echo "Downloading PaperMC ${TARGET_VERSION} from ${downloadUrl} ..."
    
    curl -fsSL -o "${OUTPUT_PATH}" "${downloadUrl}"
    
    if [ ! -f "${OUTPUT_PATH}" ]; then
      echo "ERROR: failed to download from ${downloadUrl} (status=$?)"
      exit 3
    fi
else
    echo "Stopped because ${OUTPUT_PATH} already exists"
    exit 3
fi