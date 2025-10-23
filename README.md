# BasicStateCodelab

Here is the learning code I generated manually by interacting with the codelab.

Here are my thoughts of what I liked about the codelab.

## Thoughts

### Incremental re-factoring

This is an elegant procedure to build up the concepts of state.

The state is initially in the scope of the Composable functions.

Then the state is hoisted.

Then the state is re-factored to a view model.

Then item data used in state is put in data (item) classes.

The view model survives configuration changes. This simplifies the UI state logic.

One quote I liked related to state hoisting is:

```
Key Point: When hoisting state, there are three rules to help you figure out where state should go:

State should be hoisted to at least the lowest common parent of all composables that use the state (read).
State should be hoisted to at least the highest level it may be changed (write).
If two states change in response to the same events they should be hoisted to the same level.
You can hoist the state higher than these rules require, but if you don't hoist the state high enough, it might be difficult or impossible to follow unidirectional data flow.
```

### rememberSavable

It is a convenience composable function. It defines a Saver for remember {} that will work for 99%
of cases. However, this is where a feature would write its own rememberSpecialSavable composable
function to implement a sophisticated Saver.
