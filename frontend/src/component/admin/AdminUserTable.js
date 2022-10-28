import * as React from 'react';
import PropTypes from 'prop-types';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableFooter from '@mui/material/TableFooter';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import IconButton from '@mui/material/IconButton';
import FirstPageIcon from '@mui/icons-material/FirstPage';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';
import LastPageIcon from '@mui/icons-material/LastPage';
import {useEffect} from "react";
import {AdminService} from "../../service/AdminService";
import Button from "@mui/material/Button";
import TableHead from "@mui/material/TableHead";

function TablePaginationActions(props) {
  const theme = useTheme();
  const { count, page, rowsPerPage, onPageChange } = props;

  const handleFirstPageButtonClick = (event) => {
    onPageChange(event, 0);
  };

  const handleBackButtonClick = (event) => {
    onPageChange(event, page - 1);
  };

  const handleNextButtonClick = (event) => {
    onPageChange(event, page + 1);
  };

  const handleLastPageButtonClick = (event) => {
    onPageChange(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
  };

  return (
      <Box sx={{ flexShrink: 0, ml: 2.5 }}>
        <IconButton
            onClick={handleFirstPageButtonClick}
            disabled={page === 0}
            aria-label="first page"
        >
          {theme.direction === 'rtl' ? <LastPageIcon /> : <FirstPageIcon />}
        </IconButton>
        <IconButton
            onClick={handleBackButtonClick}
            disabled={page === 0}
            aria-label="previous page"
        >
          {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
        </IconButton>
        <IconButton
            onClick={handleNextButtonClick}
            disabled={page >= Math.ceil(count / rowsPerPage) - 1}
            aria-label="next page"
        >
          {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
        </IconButton>
        <IconButton
            onClick={handleLastPageButtonClick}
            disabled={page >= Math.ceil(count / rowsPerPage) - 1}
            aria-label="last page"
        >
          {theme.direction === 'rtl' ? <FirstPageIcon /> : <LastPageIcon />}
        </IconButton>
      </Box>
  );
}

TablePaginationActions.propTypes = {
  count: PropTypes.number.isRequired,
  onPageChange: PropTypes.func.isRequired,
  page: PropTypes.number.isRequired,
  rowsPerPage: PropTypes.number.isRequired,
};

function createData(fullName, role) {
  return { fullName, role };
}



const rowlar = [
  createData('Cupcake', 305),
  createData('Donut', 452),
  createData('Eclair', 262,),
  createData('Frozen yoghurt', 159,),
  createData('Gingerbread', 356, 16.0),
  createData('Honeycomb', 408, 3.2),
  createData('Ice cream sandwich', 237),
  createData('Jelly Bean', 375),
  createData('KitKat', 518),
  createData('Lollipop', 392),
  createData('Marshmallow', 318),
  createData('Nougat', 360),
  createData('Oreo', 437)].sort((a, b) => (a.calories < b.calories ? -1 : 1));

export default function AdminUserTable() {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [rows, setRows] = React.useState([]);

  useEffect(() => {
      AdminService.adminGetAllUsers().then(response => {
          const rowsObject = response.data
          setRows(rowsObject)

      })
  }, [])
    console.log(rows)


  // Avoid a layout jump when reaching the last page with empty rows.
  const emptyRows =
      page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rowlar.length) : 0;

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const roleParser = (str) => {
      let string = str.replace("[", "").replace("]", "").toLowerCase()
      return string.charAt(0).toUpperCase() + string.slice(1)
  }

  const handleEnableAccountButton = (userId) => {
      AdminService.adminEnableUserById(userId).then(response => (
          console.log(response)
      ))

  }

  const handleBanUserButton = (userId) => {
      AdminService.adminBanUserByUserId(userId).then(response => (
          console.log(response)
      )).catch(error => (
          console.log(error)
      ))
  }

  const handleUnbanUserButton = (userId) => {
      AdminService.adminUnbanUserByUserId(userId).then(response => (
          console.log(response)
      ))
  }

  const enableAccountButton = (userId) => {
      return (
          <Button variant={"contained"} onClick={() => handleEnableAccountButton(userId)}>Enable</Button>
      )
  }

  const banUserButton = (userId) => {

      return (
          <Button variant={"contained"} onClick={() => handleBanUserButton(userId)}>Ban</Button>
      )
  }

  const unbanUserButton = (userId) => {
      return (
          <Button variant={"contained"} onClick={() => handleUnbanUserButton(userId)}>Unban</Button>
      )
  }

  return (
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 500 }} aria-label="custom pagination table">
            <TableHead>
                <TableRow>
                    <TableCell>Full name</TableCell>
                    <TableCell>Role</TableCell>
                    <TableCell>Enable Account</TableCell>
                    <TableCell>Ban</TableCell>
                    <TableCell>Unban</TableCell>
                </TableRow>
            </TableHead>
          <TableBody>
            {(rowsPerPage > 0
                    ? rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    : rows
            ).map((row) => (
                <TableRow key={row.fullname}>
                  <TableCell component="th" scope="row">
                    {row.fullname}
                  </TableCell>
                  <TableCell style={{ width: 100 }} align="left">
                    {roleParser(row.roles)}
                  </TableCell>
                  <TableCell style={{ width: 100 }} align="left">
                    {enableAccountButton(row.userId)}
                  </TableCell>
                    <TableCell style={{ width: 100}} align="left">
                        {banUserButton(row.userId)}
                    </TableCell>
                    <TableCell style={{ width: 100}} align="left">
                        {unbanUserButton(row.userId)}
                    </TableCell>
                </TableRow>
            ))}

            {emptyRows > 0 && (
                <TableRow style={{ height: 53 * emptyRows }}>
                  <TableCell colSpan={6} />
                </TableRow>
            )}
          </TableBody>
          <TableFooter>
            <TableRow>
              <TablePagination
                  rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
                  colSpan={3}
                  count={rowlar.length}
                  rowsPerPage={rowsPerPage}
                  page={page}
                  SelectProps={{
                    inputProps: {
                      'aria-label': 'rows per page',
                    },
                    native: true,
                  }}
                  onPageChange={handleChangePage}
                  onRowsPerPageChange={handleChangeRowsPerPage}
                  ActionsComponent={TablePaginationActions}
              />
            </TableRow>
          </TableFooter>
        </Table>
      </TableContainer>
  )
}
