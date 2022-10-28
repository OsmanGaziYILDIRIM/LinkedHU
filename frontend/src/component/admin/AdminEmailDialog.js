import {useState} from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import TextField from "@mui/material/TextField";
import DialogActions from "@mui/material/DialogActions";
import MailOutlineIcon from '@mui/icons-material/MailOutline';
import {MailService} from "../../service/MailService";

const defaultFormValues = {
    subject: "",
    content: ""
}

export default function AdminEmailDialog() {

    const [formValues, setFormValues] = useState(defaultFormValues);
    const handleInputChange = (e) => {
        const {name, value} = e.target
        setFormValues({...formValues, [name]: value})
    }

    const [open, setOpen] = useState(false)

    const handleOpen = () => {
        setOpen(true)
    }

    const handleClose = () => {
        setOpen(false)
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        MailService.bulkEmail(formValues.subject, formValues.content)
            .then(() => {
                console.log("Email sent")
                console.log("Subject: ", formValues.subject, "Content: ", formValues.content)
                handleClose()
            })

    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="form-group">
                <Button variant="contained" color="primary" onClick={handleOpen} >
                    Send Email to All Users
                </Button>
                <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
                    <DialogTitle id="form-dialog-title">
                        Send Email to All Users<
                        /DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            Please fill in the form below to send an email to all users.
                        </DialogContentText>
                        <TextField
                            required
                            margin="dense"
                            id="subject"
                            label="Subject"
                            name="subject"
                            type={"text"}
                            fullWidth
                            variant="standard"
                            onChange={handleInputChange}
                            defaultValue={formValues.subject}
                        />
                        <TextField
                            required
                            margin="dense"
                            id="content"
                            label="Content"
                            name="Content"
                            type={"text"}
                            fullWidth
                            variant="standard"
                            onChange={handleInputChange}
                            defaultValue={formValues.content}
                            multiline
                            rows={4}
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">Cancel</Button>
                        <Button type="submit" onClick={handleSubmit} color="primary">Send</Button>
                    </DialogActions>
                </Dialog>
            </div>
        </form>
    )
}