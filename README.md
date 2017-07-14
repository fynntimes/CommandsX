# CommandsX

An advanced annotation-based commands library for Minecraft.

## Synopsis

### The Problem

Current annotation-based command libraries don't have the flexibility that's possible
with a manual command system. The abilities to have inline arguments (arguments preceding
sub-commands within a command), validate arguments easily, and have localizable descriptions
are not present in many of the solutions available today. Additionally, the available solutions
are often hard to set up, and are poorly documented.

### The Solution

CommandsX aims to relieve these shortcomings. It is an annotation-based commands library that
is flexible and simple to use. Its features are listed here.

* Define commands, arguments, and flags with annotations.
* Inline arguments, which are arguments that precede sub-commands. These are useful for
commands that represent objects. Here is an example:
```java
class MineUtil {
    @Command(identifier="mine {name} reset")
    public void resetMine(@Arg(name="name") Mine mine) {
        // ... That's right, we just did that.
    }
}
```
* Command caching and lazy registration to boost performance, since commands won't be
loaded into memory until they're actually used.
* Customizable templates for help and description screens, for maximum flexibility.
* The help screen, though auto generated, can be overridden (finally!).
* Localization support for command descriptions and usage, using `@lang[identifier]` queries.
* More, once I think of it.

## Code Example
This code sample is specifically for Bukkit/Spigot, but this works with other platforms too!
```java
class MineUtil {
    
    @Command(identifier="mine {name} create", permissions = "mines.create", desc = "Creates a new mine.")
    public void createMine(Player sender, @Arg(name = "name") String name) {
        Mine mine = new Mine(name);
        mine.setArea(sender.getCurrentArea());
    }
    
    // Notice that we can pass in CommandSender for commands that can be used by the console.
    @Command(identifier="mine {name} addblock", permissions = "mines.addblock", desc = "Adds a block to a mine.", longDesc = "Adds a block to a mine. The chance of the block appearing should be out of 100%.")
    public void addBlockToMine(CommandSender sender, 
                            @Arg(name = "name") Mine mine, 
                            @Arg(name = "block", desc = "The name or ID of the block to add.") Block block, @Arg(name="chance", validators="[min=0][max=100]") double chance) {
        mine.addBlock(block, chance);
    }
    
}
```

## Installation
The project is currently under heavy development, and an installation guide will come after it's usable.

## API Reference
API reference will be available after the project is usable.

## Contributors
Faizaan Datoo - [@SirFaizdat](http://twitter.com/@sirfaizdat) - [faizaand.me](https://faizaand.me)

## License
MIT License

Copyright (c) 2017 Faizaan Datoo.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.