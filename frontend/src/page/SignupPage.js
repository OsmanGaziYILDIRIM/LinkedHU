import Signup from "../component/signup/SignupStudent";
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import {useState} from "react";
import SignupAcademician from "../component/signup/SignupAcademician";
import SignupGraduate from "../component/signup/SignupGraduate";


export default function () {
    const [form, setForm] = useState(<SignupAcademician/>);

    const [alignment, setAlignment] = useState('academician');

    const handleChange = (event, newAlignment) => {
        setAlignment(newAlignment);
        if (newAlignment === 'student') {
            setForm(<Signup/>);
        } else if (newAlignment === 'academician') {
            setForm(<SignupAcademician/>);
        } else if (newAlignment === 'graduate') {
            setForm(<SignupGraduate/>);
        }
    };

    return (
        <section className="signUpForm">
            <br/>
            <div style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
            }}>


                <ToggleButtonGroup
                    color="primary"
                    value={alignment}
                    exclusive
                    onChange={handleChange}
                >
                    <ToggleButton value="student">Student</ToggleButton>
                    <ToggleButton value="academician">Academician</ToggleButton>
                    <ToggleButton value="graduate">Graduate</ToggleButton>
                </ToggleButtonGroup>
            </div>
            {form}
        </section>
    );
}