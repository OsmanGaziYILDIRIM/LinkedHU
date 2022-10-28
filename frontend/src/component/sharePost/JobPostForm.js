import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import MenuItem from "@mui/material/MenuItem";
import {FormControlLabel, FormLabel, RadioGroup} from "@mui/material";
import FormControl from "@mui/material/FormControl";
import Radio from '@mui/material/Radio';
import {useState} from "react";
import WorkIcon from '@mui/icons-material/Work';
import {PostService} from "../../service/PostService";

const currencies = [
    {
        value: 'TRY',
        label: '₺',
    },
    {
        value: 'USD',
        label: '$',
    },
    {
        value: 'EUR',
        label: '€',
    },
    {
        value: 'BTC',
        label: '฿',
    },
    {
        value: 'JPY',
        label: '¥',
    },
];

const defaultValues = {
    position: "",
    location: "",
    company: "",
    salary: 0,
    currency: "TRY",
    workType: "onsite",
    description: "",
};

export default function JobPostForm({updateMode = false,postId, postType, userId, positionName, location, companyName, salary, currency, workType, jobDescription}) {

    const [formValues, setFormValues] = useState(defaultValues);
    const handleInputChange = (e) => {

        const {name, value} = e.target;
        setFormValues({
            ...formValues,
            [name]: value,
        });
    };

    const [open, setOpen] = React.useState(false);


    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {

        setOpen(false);
    };
    const handleSubmit = (event) => {
        // log the form data
        event.preventDefault();
        PostService.createJobPost(localStorage.getItem("userId"), formValues.description, formValues.position, formValues.location,
            formValues.company, formValues.salary, formValues.currency, formValues.workType).then(res => {
            setOpen(false);
            console.log("Job post created successfully");
            window.location.reload();
        })
        setOpen(false);
    }
    const handleUpdate = (event) => {
        PostService.updateJobPost(postId, postType,localStorage.getItem("userId"),
            formValues.position, formValues.location, formValues.company,
            formValues.salary, formValues.currency, formValues.workType, formValues.description).then(() => {
                window.location.reload();
            }
        )
        setOpen(false);
    }
    if (updateMode) {
        defaultValues.workType = workType;
        defaultValues.position = positionName;
        defaultValues.location = location;
        defaultValues.company = companyName;
        defaultValues.salary = salary;
        defaultValues.currency = currency;
        defaultValues.description = jobDescription;
    }
    return (
        <form onSubmit={handleSubmit}>
            <div>
                {updateMode ?
                    <MenuItem onClick={handleClickOpen}>Update</MenuItem>
                    :
                    <div className="messageSender__option" onClick={handleClickOpen}>
                        <WorkIcon style={{color: "blue"}}/>
                        <h3>Job</h3>
                    </div>
                }


                <Dialog open={open} onClose={handleClose}>
                    <DialogTitle>Job Post</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            Please fill in the form below to post a job.
                        </DialogContentText>
                        <TextField
                            required
                            margin="dense"
                            id="position"
                            name="position"
                            label="Position Name"
                            type="text"
                            fullWidth
                            variant="standard"
                            defaultValue={formValues.position}
                            onChange={handleInputChange}
                        />
                        <TextField
                            required
                            margin="dense"
                            id="company"
                            name="company"
                            label="Company Name"
                            type="text"
                            fullWidth
                            variant="standard"
                            defaultValue={formValues.company}
                            onChange={handleInputChange}
                        />
                        <TextField
                            required
                            margin="dense"
                            id="location"
                            name="location"
                            label="Location"
                            type="text"
                            fullWidth
                            variant="standard"
                            defaultValue={formValues.location}
                            onChange={handleInputChange}
                        />
                        <br/>
                        <br/>
                        <div>
                            <TextField
                                required
                                id="salary"
                                name="salary"
                                label="Salary"
                                type="number"
                                inputProps={{"min": "0", "max": "1000000", step: "100"}}
                                InputLabelProps={{
                                    shrink: true,
                                }}
                                variant="standard"
                                defaultValue={formValues.salary}
                                onChange={handleInputChange}
                            />
                            <TextField
                                required
                                id="currency"
                                name="currency"
                                select
                                label="Currency"
                                SelectProps={{
                                    native: true,
                                }}
                                helperText="Please select your currency"
                                variant="standard"
                                defaultValue={formValues.currency}
                                onChange={handleInputChange}
                            >
                                {currencies.map((option) => (
                                    <option key={option.value} value={option.value}>
                                        {option.label}
                                    </option>
                                ))}
                            </TextField>
                        </div>
                        <FormControl>
                            <FormLabel id="demo-row-radio-buttons-group-label">Work Type</FormLabel>
                            <RadioGroup
                                row
                                aria-labelledby="demo-row-radio-buttons-group-label"
                                name="workType"
                                defaultValue={formValues.workType}
                                onChange={handleInputChange}
                            >
                                <FormControlLabel value="onsite" control={<Radio/>} label="On-site"/>
                                <FormControlLabel value="remote" control={<Radio/>} label="Remote"/>

                            </RadioGroup>
                        </FormControl>
                        <TextField
                            required
                            margin="dense"
                            id="description"
                            name="description"
                            label="Job Description"
                            type="description"
                            fullWidth
                            variant="standard"
                            multiline
                            rows={4}
                            defaultValue={formValues.description}
                            onChange={handleInputChange}
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose}>Cancel</Button>
                        {updateMode ?
                            <Button onClick={handleUpdate}>Update</Button>
                            :
                            <Button onClick={handleSubmit}>Share</Button>
                        }

                    </DialogActions>
                </Dialog>
            </div>
        </form>
    );
}
