import React from 'react'
import background from "../../assets/images/background.jpg"
import background1 from "../../assets/images/background1.png"
import background2 from "../../assets/images/background2.jpg"
import {useState} from 'react'
import {Carousel} from "react-bootstrap";

const BackgroundImageSlider = () => {

    const backgrounds = [background, background1, background2];
    const [index, setIndex] = useState(0);

    const handleSelect = (selectedIndex) => {
        setIndex(selectedIndex);
    };

    return (
        <div className='background-slider'>
            <Carousel activeIndex={index} onSelect={handleSelect} interval={20000}>
                {backgrounds.map((background, idx) => (
                    <Carousel.Item key={idx}>
                        <img
                            className='d-block w-100'
                            src={background}
                            alt={`Slide ${idx}`}
                        />
                    </Carousel.Item>
                ))}
            </Carousel>
        </div>
    );
};

export default BackgroundImageSlider;
