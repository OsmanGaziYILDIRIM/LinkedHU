import {createTheme} from "@mui/material/styles";

export default ({ palette, spacing }) => {
    const theme = createTheme();
    const radius = theme.spacing(2.5);
    const size = theme.spacing(4);
    const rightBgColor = theme.palette.primary.main;
    // if you want the same as facebook messenger, use this color '#09f'
    return {

        avatar: {
            width: size,
            height: size,
        },
        leftRow: {
            textAlign: 'left',
        },
        rightRow: {
            textAlign: 'right',
        },
        msg: {
            padding: theme.spacing(1, 2),
            borderRadius: 4,
            marginBottom: 4,
            display: 'inline-block',
            wordBreak: 'break-word',
            fontFamily:
            // eslint-disable-next-line max-len
                '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol"',
            fontSize: '14px',
        },
        left: {
            borderTopRightRadius: radius,
            borderBottomRightRadius: radius,
            backgroundColor: theme.palette.grey[100],
        },
        right: {
            borderTopLeftRadius: radius,
            borderBottomLeftRadius: radius,
            backgroundColor: rightBgColor,
            color: theme.palette.common.white,
        },
        leftFirst: {
            borderTopLeftRadius: radius,
        },
        leftLast: {
            borderBottomLeftRadius: radius,
        },
        rightFirst: {
            borderTopRightRadius: radius,
        },
        rightLast: {
            borderBottomRightRadius: radius,
        },
    };
};