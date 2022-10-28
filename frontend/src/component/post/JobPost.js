
import JobPic from '../../asset/image/jobLogo.png';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import "./Post.css";
import ApplyButton from "./ApplyForm"
import Grid from "@mui/material/Grid";
import LocationOnIcon from '@mui/icons-material/LocationOn';


export default function JobPost({fullName, email, jobTitle, jobDescription, jobLocation, jobCompany, jobSalary, currency}) {
    return (
        <Grid className={"jobCard"} sx={{ display: 'flex'/*, alignItems:"center"*/ } }>
            <CardMedia className={"mediaPhoto"}
                component="img"
                sx={{ width: 151 }}
                image={JobPic}
            />
            <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                <CardContent sx={{ flex: '1 0 auto' }}>
                    <Typography component="div" variant="h5">
                        {jobTitle}
                    </Typography>
                    <Box sx={{display: 'flex', alignItems: 'center', pl: 1, pb: 1, padding:0}}>

                        <Box sx={{display: 'flex', alignItems: 'center', width: '50%', paddingLeft: 1, padding:0}}>
                            <Typography component="div" variant="h7" sx={{marginBottom:1}}>
                                {jobCompany}
                            </Typography>
                        </Box>
                        <Box className={"applyNowButton"} sx={{display: 'flex', alignItems: 'center', width: '50%'}}>
                            <Typography component="div" variant="h7">
                                {jobSalary} {currency}
                            </Typography>

                        </Box>

                    </Box>


                    <Typography variant="subtitle1" color="text.secondary" component="div" >
                        {jobDescription}
                    </Typography>
                </CardContent>
                <Box  sx={{ display: 'flex', alignItems: 'center', pl: 1, pb: 1 }}>

                    <Box sx={{display: 'flex', alignItems: 'center', width: '50%', paddingLeft:1 }}>
                        <LocationOnIcon />
                        <Typography variant="subtitle1" color="black" component="div">
                            {jobLocation}
                        </Typography>
                    </Box>
                    <Box className={"applyNowButton"} sx={{display: 'flex', alignItems: 'center', width: '50%' }}>
                        <ApplyButton
                            fullName={fullName}
                            email={email}
                        />

                    </Box>


                </Box>
            </Box>

        </Grid>
    );
}
