import React, {Fragment} from 'react';
import {CardImg} from "react-bootstrap";
import placeholder from "../../assets/images/placeholder.jpg";

const UserImage = ({userId, userPhoto, altText = "User photo"}) => {
    return (
        <Fragment>
            {userPhoto ? (
                <CardImg src={`data:image/png;base64, ${userPhoto}`}
                         className={"user-image"}
                         alt={altText}
                />

            ) : (
                <CardImg src={placeholder}
                         className={"user-image"}
                         alt={altText}
                />
            )}

        </Fragment>
    );
};

export default UserImage;