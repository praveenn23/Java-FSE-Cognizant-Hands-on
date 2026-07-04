import React, { Component } from 'react';
import './App.css';
import CurrencyConvertor from './CurrencyConvertor';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            count: 0
        };
        this.increment = this.increment.bind(this);
        this.decrement = this.decrement.bind(this);
        this.sayHello = this.sayHello.bind(this);
        this.sayWelcome = this.sayWelcome.bind(this);
        this.onPress = this.onPress.bind(this);
    }



    increment() {
        if (this.state.count === 0) {
            this.sayHello();
        }
        this.setState({ count: this.state.count + 1 });
    }

    decrement() {
        this.setState({ count: this.state.count - 1 });
    }

    sayHello() {
        alert("Hello!");
    }

    sayWelcome(message) {
        alert(message);
    }

    onPress(event) {
        alert("I was clicked");
    }

    render() {
        return (
            <div>
                <div style={{ padding: '20px', margin: '20px' }}>
                    <h2>Counter: {this.state.count}</h2>
                    <button onClick={this.increment} style={{ marginBottom: '10px' }}>Increment</button>
                    <br />
                    <button onClick={this.decrement}>Decrement</button>
                    <br />
                    <button onClick={() => this.sayWelcome("welcome")}>Say Welcome</button>
                    <br />
                    <button onClick={this.onPress}>OnPress</button>
                </div>

                <CurrencyConvertor />
            </div>
        );
    }
}

export default App;
