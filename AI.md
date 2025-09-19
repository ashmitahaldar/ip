# AI.md

## AI Tool Usage Record for MayoBot - CS2103T AY25/26 S1 iP

### Tool Used
**GitHub Copilot** - AI programming assistant integrated into IntelliJ IDEA 2024.3.6

### Usage Summary by Development Areas

#### JUnit Test Writing and Fixing
- **Scope**: Comprehensive test suite development and error resolution
- **Activities**:
    - Systematically fixed failing unit tests across command classes (`EventCommandTest`, `DeadlineCommandTest`, `FindCommandTest`)
    - Standardized test patterns and assertion strategies
    - Resolved date format inconsistencies between test expectations and actual implementation
    - Implemented proper exception testing with `assertThrows()` patterns

#### Exception Handling Architecture
- **Scope**: Custom exception implementation and integration
- **Activities**:
    - Refactored command classes to use specific exceptions (`FindException`) instead of generic ones
    - Implemented proper error propagation and handling patterns
    - Enhanced error messaging consistency across the application

#### JavaDoc Documentation Writing
- **Scope**: Comprehensive API documentation generation
- **Activities**:
    - Generated professional-grade JavaDoc comments for classes and methods
    - Created standardized documentation patterns with proper parameter descriptions, return values, and cross-references
    - Maintained consistency in documentation style across the codebase

#### Code Refactoring and SOLID Principles
- **Scope**: Code quality improvement and architectural enhancement
- **Activities**:
    - Applied Single Level of Abstraction Principle (SLAP) by breaking down complex methods
    - Identified opportunities for DRY principle enforcement across similar command structures
    - Suggested method decomposition strategies for large, monolithic methods
    - Improved code readability through consistent naming conventions and structure

#### AI-Assisted Documentation and Output Standardization
- **Scope**: README.md output block alignment and documentation enhancement
- **Activities**:
    - Used GitHub Copilot to systematically update README.md output examples for all major commands (todo, deadline, event, list, mark, unmark, delete, find)
    - Ensured output blocks match the exact formatting, emojis, and trailing symbols as specified in command classes
    - Improved documentation accuracy and user experience by reflecting true application behavior
    - Validated changes for completeness and consistency

### What Worked Well

1. **Pattern Recognition**: Excellent at identifying repetitive code structures and suggesting consistent refactoring approaches
2. **Test Strategy Development**: Effectively generated comprehensive test scenarios covering edge cases and error conditions
3. **Documentation Standards**: Maintained professional documentation quality with minimal manual editing required
4. **Code Analysis**: Successfully identified architectural improvements and principle violations

### What Didn't Work as Expected

1. **Context Dependencies**: Required multiple iterations to understand project-specific conventions and requirements
2. **Complex Refactoring**: Large-scale architectural changes needed more human oversight and decision-making
3. **Integration Challenges**: Some suggestions required manual adaptation to fit existing codebase patterns

### Time Savings and Development Efficiency

- **Estimated Time Saved**: 6-7 hours across all development activities
- **Key Efficiency Gains**:
    - **Test Development**: Reduced test writing time by ~70% through pattern-based generation
    - **Documentation**: Near-instantaneous JavaDoc creation compared to manual writing
    - **Debugging**: Rapid identification of systematic issues across multiple files
    - **Refactoring**: Quick generation of cleaner code alternatives following SOLID principles

### Overall Assessment

GitHub Copilot significantly accelerated development velocity, particularly in:
- **Systematic Problem Solving**: Identifying and fixing similar issues across multiple components
- **Code Quality Enhancement**: Suggesting improvements that align with software engineering best practices
- **Documentation Consistency**: Maintaining uniform documentation standards throughout the project

The tool proved most valuable for pattern-based tasks and systematic improvements, while requiring human judgment for 
architectural decisions and complex business logic implementation. The combination of AI assistance with human oversight created an efficient development workflow that maintained code quality while accelerating delivery.