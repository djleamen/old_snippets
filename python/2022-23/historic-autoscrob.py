# Description: An unfinished Python script that listens to audio input, detects music, 
# and scrobbles it to Last.fm automatically.

import pyaudio # type: ignore
import numpy as np # type: ignore
import pylast  # type: ignore # PyLast library for Last.fm integration
import acr_cloud # type: ignore
import requests # type: ignore
import time  # For timestamp in scrobbling

# Configure ACRCloud credentials
acrcloud_config = {
    'host': '',
    'access_key': '',
    'access_secret': ''
}

# Configure Last.fm credentials
lastfm_api_key = ''
lastfm_api_secret = ''
lastfm_username = ''
lastfm_password = ''

acrcloud_recognizer = acr_cloud.ACRCloud(
    acrcloud_config['host'],
    acrcloud_config['access_key'],
    acrcloud_config['access_secret']
)

# Initialize Last.fm network
lastfm_network = pylast.LastFMNetwork(
    api_key=lastfm_api_key,
    api_secret=lastfm_api_secret,
    username=lastfm_username,
    password_hash=lastfm_password_hash # type: ignore
)

# Audio parameters
FORMAT = pyaudio.paInt16
CHANNELS = 1
RATE = 44100
CHUNK_SIZE = 4096

# Function to process audio stream and perform music recognition
def process_audio():
    """ Continuously listen for audio, detect songs, and scrobble to Last.fm """
    audio = pyaudio.PyAudio()
    stream = audio.open(
        format=FORMAT,
        channels=CHANNELS,
        rate=RATE,
        input=True,
        frames_per_buffer=CHUNK_SIZE
    )

    print("Listening for music... Press Ctrl+C to stop.")

    try:
        while True:
            # Read audio stream data
            data = np.frombuffer(stream.read(CHUNK_SIZE), dtype=np.int16)

            # Perform music recognition on the audio data
            result = acrcloud_recognizer.recognize(data.tobytes(), 0)

            # Check if the song was identified successfully
            if result['status']['code'] == 0 and 'metadata' in result:
                metadata = result['metadata']['music'][0]
                artist = metadata['artists'][0]['name']
                title = metadata['title']

                print(f"Identified song: {artist} - {title}")

                # Scrobble the identified song to Last.fm
                timestamp = int(time.time())  # Current time in UNIX timestamp
                lastfm_network.scrobble(artist=artist, title=title, timestamp=timestamp)
                print(f"Scrobbled: {artist} - {title}")

                # Wait for a while before identifying the next song (to avoid repeated scrobbles)
                time.sleep(10)
            else:
                print("No song identified.")
    except KeyboardInterrupt:
        print("\nStopping audio processing.")
    finally:
        stream.stop_stream()
        stream.close()
        audio.terminate()

if __name__ == "__main__":
    process_audio()
