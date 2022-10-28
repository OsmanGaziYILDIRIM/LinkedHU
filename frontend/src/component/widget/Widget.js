import React from 'react';
import './Widget.css';
import FiberManualRecordIcon from '@mui/icons-material/FiberManualRecord';
import { makeStyles } from "@mui/styles";


const WidgetStyle = makeStyles((theme) => ({
    job_text: {
        marginTop: "0px",
        marginBottom: "0px",
    },

}));

function Widgets() {


    const classes = WidgetStyle();
    const newsArticle = (heading, subtitle, color) => (

        <div className="widget_article">
            <div className="widgets_articleleft" style={{color: color}}>

                <FiberManualRecordIcon/>
            </div>

            <div className="widgets_articleright">
                <h4 className={classes.job_text}>
                    {heading}
                </h4>
                <p>{subtitle}</p>
            </div>
        </div>
    );

    return (
        <div className="widgets">
            <div className="widget_header">
                <h2>Top Jobs</h2>

            </div>

            {newsArticle("Frontend Developer", "Ankara - On-site", "#"+Math.floor(Math.random()*16777215).toString(16))}

            {newsArticle("Backend Developer", "Istanbul - Remote", "#"+Math.floor(Math.random()*16777215).toString(16))}

            {newsArticle("Data Scientist", "London - Remote", "#"+Math.floor(Math.random()*16777215).toString(16))}
        </div>
    )
}

export default Widgets