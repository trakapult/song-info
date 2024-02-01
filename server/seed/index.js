const { sequelize, User, Song, Bookmark, History } = require('../src/models')
const Promise = require('bluebird')
const users = require('./user.json')
const songs = require('./song.json')
const bookmarks = require('./bookmark.json')
const histories = require('./history.json')

sequelize.sync({force: true})
    .then(async function () {
        await Promise.all(
            users.map(user => {
                User.create(user)
            })
        )
        await Promise.all(
            songs.map(song => {
                Song.create(song)
            })
        )
        await Promise.all( // should be after User.create and Song.create
            bookmarks.map(bookmark => {
                Bookmark.create(bookmark)
            })
        )
        await Promise.all(
            histories.map(history => {
                History.create(history)
            })
        )
    })