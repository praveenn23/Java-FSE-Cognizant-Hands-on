import React from 'react';

function ListofPlayers(props) {
    return (
        props.players.map((item, index) => {
            return (
                <div key={index}>
                    <li> Mr. {item.name}<span> {item.score} </span></li>
                </div>
            )
        })
    );
}

export default ListofPlayers;
