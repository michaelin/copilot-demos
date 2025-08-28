using System;
using System.Collections.Generic;

public class DanishToEnglishTranslator
{
    private Dictionary<string, string> translations = new Dictionary<string, string>
    {
        { "hej", "hello" },
        { "farvel", "goodbye" },
        { "tak", "thank you" },
        // Add more translations as needed
    };

    public string Translate(string danishWord)
    {
        if (translations.TryGetValue(danishWord, out string englishWord))
        {
            return englishWord;
        }
        else
        {
            throw new ArgumentException("No translation found for the provided word.");
        }
    }
}