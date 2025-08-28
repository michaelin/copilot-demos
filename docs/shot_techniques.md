# Edit mode - Shot Techinques

|             |                                                                                                   |
|-------------|---------------------------------------------------------------------------------------------------|
| Purpose     | Show Edit mode by introducing Shot techniques                                                     |
| Description | Add unit tests and documentation to a file in Edit mode using zero-, one- and few-shot techinques |
| File        | [`demos/shot_techniques/calculator.cs`](shot_techniques/calculator.cs) |

### Zero-Shot Prompting

Use Claude Sonnet 4

Switch to Edit mode

```text
Can you help me unit test this class?
```

### One-Shot Prompting

Add a single example of how you want the code to look

```text
Can you help me unit test this class. Use the following example:

[Test]
public void Divide_validInput_shouldDivide()
{
    // Arrange
    double a = 6;
    double b = 3;

    // Act
    double result = _calculator.Divide(a, b);

    // Assert
    result.Should().Be(2);
}
```

Alternatively without an example, but with more context:

```text
Help me write unit test for this class. Use the arrange act assert method, nunit and name the function prototypes by the operator, pre-condition and expected output.
```

Iterate by adding more detail:

```text
Help me write unit test for this class. Use the arrange act assert method, nunit and name the function prototypes by the operator, pre-condition and expected output.
Use the FluentAssertions framework for assertions.
```

### Few-Shot prompting

Add unittests to `#translate.cs` by referencing `calculatorTests.cs`

```text
Can you help me unit test this class? Use the same format as in #file:CalculatorTests.cs 
```
