/*
This program 

*/

// These statements include the libraries that we are using, and tell the compiler that we want to use code from them. 'Libraries' are pieces of code that have already been written (typically by someone other than yourself), so that you don't have to write everything from scratch every time you want to make a program.

// This library is the Arduino Standard Library, which provides all the basic functionality that the hardware provides (for example: timing, input and output, the loop() and setup() functions).
#include <Arduino.h>

// This is the FastLED library, which is used to control the WS2812B LEDs that are on the board. It provides a lot of very useful functions, and takes care of all the low level data handling for us.
#include <FastLED.h>

#include <characters.h>

// These are constants that we define for our program, such as what type of LEDs we are using, how bright we want the LED to be, and the refresh rate (update rate) of the LED.
// How many LEDs are in each coloumn and row.
constexpr size_t ROWS = 5;
constexpr size_t COLS = 77;

// How bright the LEDs should be, on a scale from 0 to 255.
#define BRIGHTNESS 255
// The specific type of LED being controlled, so the FastLED library knows how to format the transmitted colour data.
#define LED_TYPE WS2812B
// The order in which the LED accepts colour data - [R]ed, [G]reen, [B]lue.
#define COLOR_ORDER RGB

CRGB leds[ROWS][COLS] = {0};
CRGB bottom[COLS];

void writeText(const char text[], const CRGB colour, const size_t x, const size_t y, CRGB (&ledArray)[ROWS][COLS])
{
  for (int textIndex = 0; textIndex < strlen(text); ++textIndex)
  {
    uint8_t asciiCodeOffset;
    if (text[textIndex] == ' '){
      asciiCodeOffset = 26;
    }
    else if (text[textIndex] == '!')
    {
      asciiCodeOffset = 27;
    } else {
      asciiCodeOffset = text[textIndex] - STARTING_CHAR;
    }

    for (int characterRow = 0; characterRow < CHARACTER_SIZE; ++characterRow)
    {

      for (int bit = 0; bit < CHARACTER_SIZE; ++bit)
      {
        const size_t ledRow = characterRow + (ROWS - CHARACTER_SIZE) - y;
        // Check if text overflows array, break to prevent writing out of bounds.
        if (ledRow > (ROWS - 1))
          break;
        if (ledRow < 0)
          continue;

        const size_t ledCol = (CHARACTER_SIZE - (bit + 1)) + ((CHARACTER_SIZE + 1) * textIndex) + x;
        // Check if text overflows array, break to prevent writing out of bounds.
        if (ledCol > (COLS - 1))
          continue;
        if (ledCol < 0)
          break;
        // 
        const bool isPixel = (characters[asciiCodeOffset][characterRow] >> bit) & 0x1;
        ledArray[ledRow][ledCol] = isPixel? colour : CRGB::Black;
      }
    }
  }
}

void setup()
{
  FastLED.addLeds<LED_TYPE, 23>(leds[0], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 22>(leds[1], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 21>(leds[2], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 20>(leds[3], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 10>(leds[4], COLS).setCorrection(TypicalLEDStrip);

  FastLED.addLeds<LED_TYPE, 9>(bottom, COLS).setCorrection(TypicalLEDStrip);

  FastLED.setBrightness(BRIGHTNESS);
}

void loop()
{
  for (int i = 80; i > 6; i--)
  {
    fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
    writeText("APPLAUSE!!!", CRGB::Green, i, 0, leds);

    fill_rainbow(&(bottom[0]), COLS /*led count*/, i * 10 /*starting hue*/);

    delay(50);
    FastLED.show();
  }


  delay(4000);

  for (int i = 6; i > -80; i--)
  {
    fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
    writeText("APPLAUSE!!!", CRGB::Green, i, 0, leds);

    fill_rainbow(&(bottom[0]), COLS /*led count*/, i * 10 /*starting hue*/);

    delay(50);
    FastLED.show();
  }

  for (int i = 80; i > 0; i--)
  {
    fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
    writeText("GALESI BOARD", CRGB::Red, i, 0, leds);

    fill_rainbow(&(bottom[0]), COLS /*led count*/, i * 10 /*starting hue*/);

    delay(50);
    FastLED.show();
  }

  delay(4000);

  for (int i = 0; i > -80; i--)
  {
    fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
    writeText("GALESI BOARD", CRGB::Red, i, 0, leds);

    fill_rainbow(&(bottom[0]), COLS /*led count*/, i * 10 /*starting hue*/);

    delay(50);
    FastLED.show();
  }

  // Don't look at this is nothing nothing to see here folks 

  /*

  for (int i = 5; i >= 0; i--)
  {
    fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
    writeText("    WANNA", CRGB::Blue, 0, i, leds);

    fill_rainbow(&(bottom[0]), COLS , i * 10 );

    delay(80);
    FastLED.show();
  }

  delay(4000);

  fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
  writeText(" CHA", CRGB::Red, 0, 0, leds);
  FastLED.show();

  delay(1000);

  fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
  writeText(" CHA CHA", CRGB::Green, 0, 0, leds);
  FastLED.show();

  delay(1000);

  fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
  writeText(" CHA CHA CHA", CRGB::BlueViolet, 0, 0, leds);
  FastLED.show();

  delay(4000);

  for (int i = 5; i >= 0; i--)
  {
    fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
    writeText("  AT PROM", CRGB::HotPink, 0, i, leds);

    fill_rainbow(&(bottom[0]), COLS, i * 10);

    delay(80);
    FastLED.show();
  }

  delay(4000);
  */
}