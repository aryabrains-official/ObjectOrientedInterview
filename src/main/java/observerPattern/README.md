## Observer pattern
It’s a behavioural design pattern. It specifies communication between objects: observable and observers. An observable is an object which notifies observers about the changes in its state.

## How is it different from pub sub pattern ?
The basic difference is in Observer pattern observer and observables are aware of each other and are highly coupled. In pub-sub publisher and subscriber are not aware of each other and are connected using a central broker. Hence in pub-sub the publisher and subscribers are loosely coupled.

## What's inside this package ?
There are two packages inside this package:
1. **basic**: Implements observer pattern without using any built-in interfaces.
2. **usingPropertyChangeListener**: Implements observer pattern using built-in interface PropertyChangeListener.

## Is there any other way to implement this design pattern ?
Yes java also provider another built-in interface called Observer and Observable. It's deprecated since Java 9, and reason being Observable class was not an interface, rather a class which needs to be extended, hence subclasses can't be used as observables.

