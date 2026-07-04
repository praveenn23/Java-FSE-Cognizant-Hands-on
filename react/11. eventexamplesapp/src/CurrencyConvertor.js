import React, { Component } from 'react';

class CurrencyConvertor extends Component {
    constructor(props) {
        super(props);
        this.state = {
            amount: '',
            currency: ''
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const euro = (this.state.amount / 89.51).toFixed(2);
        this.setState({ currency: euro });
        alert("Converted: " + this.state.amount + " INR = " + euro + " Euro");
    }

    render() {
        return (
            <div style={{ backgroundColor: 'white', color: 'black', padding: '20px', margin: '20px' }}>
                <h1 style={{color:'green'}}>Currency Convertor!!!</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>Amount: </label>
                    <input
                        type="number"
                        value={this.state.amount}
                        onChange={(e) => this.setState({ amount: e.target.value })}
                    />
                    <br /><br />
                    <label>Currency: </label>
                    <input
                        type="text"
                        value={this.state.currency}
                        readOnly
                    />
                    <br /><br />
                    <button type="submit" style={{marginLeft:'12%'}}>Submit</button>
                </form>
            </div>
        );
    }
}

export default CurrencyConvertor;
